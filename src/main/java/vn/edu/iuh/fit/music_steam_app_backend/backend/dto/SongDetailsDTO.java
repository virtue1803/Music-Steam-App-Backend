package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.edu.iuh.fit.music_steam_app_backend.backend.CommentDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class SongDetailsDTO {
    private Long id;
    private String title;
    private String duration;
    private String cover;
    private String playCount;
    private String genre;
    private String artistName;
    private String albumTitle;
    private List<CommentDTO> comments; // List of comments on the song
}
