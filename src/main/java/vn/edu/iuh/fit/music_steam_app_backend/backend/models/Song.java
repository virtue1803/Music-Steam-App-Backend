package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Genre;
import vn.edu.iuh.fit.music_steam_app_backend.backend.enums.Language;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "song")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @Column(name = "duration", nullable = false)
    private Duration duration;

    @NonNull
    @Column(name = "cover", nullable = false)
    private String cover; // URL ảnh bìa bài hát

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "genre", nullable = false)
    private Genre genre; // Enum liên kết đến thể loại bài hát

    @NonNull
    @Column(name = "lyrics", length = 5000)
    private String lyrics;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language; // Tham chiếu tới Enum Language

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = true)
    private Album album;
}
