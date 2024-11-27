package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Role;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "role", nullable = false)
    private Role role; // Enum liên kết tới vai trò người dùng
}
