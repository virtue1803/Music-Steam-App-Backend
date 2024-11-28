package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.dto.LoginRequest;
import vn.edu.iuh.fit.music_steam_app_backend.backend.dto.UserDto;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Role;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject BCryptPasswordEncoder

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

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);  // Gán mật khẩu đã mã hóa cho người dùng

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
        user.setPassword(userDetails.getPassword());  // Chú ý: Nếu có thay đổi mật khẩu, phải mã hóa lại
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public User createUser(UserDto userDTO) throws Exception {
        // Kiểm tra nếu email đã tồn tại
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email already exists");
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Tạo người dùng mới
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);  // Lưu mật khẩu đã mã hóa
        user.setRole(Role.USER); // Mặc định là user

        return userRepository.save(user);
    }

    public boolean isBCrypt(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }

    public User authenticate(LoginRequest loginRequest) throws Exception {
        // Tìm người dùng theo email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Kiểm tra nếu người dùng không tồn tại
        if (user == null) {
            throw new Exception("Invalid credentials");
        }

        // Kiểm tra nếu mật khẩu đã mã hóa là BCrypt
        if (!isBCrypt(user.getPassword())) {
            // Nếu mật khẩu chưa được mã hóa đúng cách, mã hóa lại và lưu vào cơ sở dữ liệu
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);  // Lưu lại mật khẩu đã mã hóa
        }

        // So sánh mật khẩu người dùng nhập với mật khẩu đã mã hóa trong cơ sở dữ liệu
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return user;
    }

}
