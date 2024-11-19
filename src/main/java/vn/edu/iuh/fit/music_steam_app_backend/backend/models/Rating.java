package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private int score; // Điểm đánh giá, ví dụ: từ 1 đến 5

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = true)
    private Song song;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = true)
    private Album album;

    @Column(name = "created_at", nullable = false)
    private String createdAt; // Ngày đánh giá
}
