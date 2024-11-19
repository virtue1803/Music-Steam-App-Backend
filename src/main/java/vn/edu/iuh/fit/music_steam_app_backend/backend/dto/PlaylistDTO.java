package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaylistDTO {
    private Long id;
    private String title;
    private String creator;
    private List<Long> songIds; // List of song IDs in the playlist
}