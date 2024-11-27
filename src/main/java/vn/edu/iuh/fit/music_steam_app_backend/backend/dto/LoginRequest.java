package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

public class LoginRequest {

    private String email;
    private String password;

    // Constructor
    public LoginRequest() {}

    // Getter và Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
