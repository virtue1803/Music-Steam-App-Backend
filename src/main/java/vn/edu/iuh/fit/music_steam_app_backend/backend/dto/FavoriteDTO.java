package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteDTO {
    private Long userId;
    private Long songId;
    private String addedAt; // Date when the song was added to favorites
}
