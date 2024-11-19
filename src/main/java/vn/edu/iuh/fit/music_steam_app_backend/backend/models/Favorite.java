package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;

@Getter
@Setter
@Entity
@Table(name = "favorite")
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("song")
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "added_at", nullable = false)
    private String addedAt; // Ngày bài hát được thêm vào yêu thích
}