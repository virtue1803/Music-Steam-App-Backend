package vn.edu.iuh.fit.music_steam_app_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistDTO {
    private Long id;
    private String name;
    private String bio;
    private String profilePic;
    private String followers;
}
