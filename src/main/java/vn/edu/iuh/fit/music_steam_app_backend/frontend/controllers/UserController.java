package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.dto.LoginRequest;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Role;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.UserService;

import java.util.List;
@CrossOrigin(origins = "https://snack-web-player.s3.us-west-1.amazonaws.com")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Thêm người dùng mới
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            // Log chi tiết để xem dữ liệu nhận được
            System.out.println("Received user: " + user);

            // Kiểm tra nếu role là null và gán mặc định nếu cần
            if (user.getRole() == null) {
                user.setRole(Role.USER);
            }

            // Xử lý tạo người dùng
            return ResponseEntity.ok(userService.createUser(user));
        } catch (RuntimeException e) {
            // Log lỗi khi gặp vấn đề
            System.out.println("Error creating user: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }



    // Xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Cập nhật người dùng
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, userDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Kiểm tra email và mật khẩu của người dùng
            User user = userService.authenticate(loginRequest);

            // Nếu người dùng không tồn tại hoặc mật khẩu không đúng
            if (user == null) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            // Nếu xác thực thành công, trả về thông tin người dùng
            return ResponseEntity.ok(user); // Trả về thông tin người dùng
        } catch (Exception e) {
            // Trả về lỗi cụ thể hơn khi mật khẩu không phải BCrypt hoặc có vấn đề khác
            if (e.getMessage().contains("Password is not encoded with BCrypt")) {
                return ResponseEntity.status(400).body("Password is not properly encoded");
            } else if (e.getMessage().contains("Invalid password")) {
                return ResponseEntity.status(401).body("Invalid password");
            } else if (e.getMessage().contains("User not found")) {
                return ResponseEntity.status(404).body("User not found");
            }
            return ResponseEntity.status(500).body("An error occurred during authentication");
        }
    }


}