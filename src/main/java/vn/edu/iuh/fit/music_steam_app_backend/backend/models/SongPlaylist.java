package vn.edu.iuh.fit.music_steam_app_backend.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.SongPlaylistId;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "song_playlist")
@NoArgsConstructor
@AllArgsConstructor
public class SongPlaylist {

    @EmbeddedId
    private SongPlaylistId id;

    @ManyToOne
    @MapsId("song")
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @MapsId("playlist")
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @Column(name = "added_at")
    private LocalDateTime addedAt; // Ví dụ: ngày bài hát được thêm vào playlist
}