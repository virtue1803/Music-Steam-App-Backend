package vn.edu.iuh.fit.music_steam_app_backend.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private String username; // Optional: Include user's name for convenience
    private Long songId;
}