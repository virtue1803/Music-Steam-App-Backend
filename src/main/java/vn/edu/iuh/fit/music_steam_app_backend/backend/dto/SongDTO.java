package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SongDTO {
    private Long id;
    private String title;
    private String duration;
    private String cover;
    private String playCount;
    private String genre;
    private Long artistId;
    private String artistName; // Optional: Include artist's name for convenience
    private Long albumId;
}
