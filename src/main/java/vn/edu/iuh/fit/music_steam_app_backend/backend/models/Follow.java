package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "follow")
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = true)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = true)
    private Playlist playlist;

    @Column(name = "followed_at", nullable = false)
    private String followedAt; // Ngày theo dõi
}
