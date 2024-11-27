package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Role;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Thêm người dùng mới
    public User createUser(User user) {
        // Kiểm tra xem username hoặc email đã tồn tại chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // Thiết lập vai trò mặc định nếu chưa có
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found!");
        }
        userRepository.deleteById(id);
    }

    // Cập nhật thông tin người dùng
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) throws Exception {
        // Kiểm tra nếu email đã tồn tại
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email already exists");
        }

        // Tạo người dùng mới
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.USER); // Mặc định là user
        return userRepository.save(user);
    }

    public User authenticate(LoginRequest loginRequest) throws Exception {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new Exception("Invalid password");
        }

        return user;
    }
}