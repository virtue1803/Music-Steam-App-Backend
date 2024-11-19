package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArtistDetailsDTO {
    private Long id;
    private String name;
    private String bio;
    private String profilePic;
    private String followers;
    private List<AlbumDTO> albums; // List of albums by the artist
    private List<SongDTO> songs; // List of songs by the artist
}