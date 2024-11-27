package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Role;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.music_steam_app_backend.backend.models.User}
 */
@Data
@AllArgsConstructor
@Getter

public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    String email;
    Role role;


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}