package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String role;
    private String token; // JWT token for authentication
}
