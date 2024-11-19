package vn.edu.iuh.fit.music_steam_app_backend.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.edu.iuh.fit.music_steam_app_backend.backend.dto.SongDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class AlbumDetailsDTO {
    private Long id;
    private String title;
    private String cover;
    private String artistName;
    private List<SongDTO> songs; // List of songs in the album
}

