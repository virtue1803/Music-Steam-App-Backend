package vn.edu.iuh.fit.music_steam_app_backend.backend.ids;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Playlist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SongPlaylistId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongPlaylistId)) return false;
        SongPlaylistId that = (SongPlaylistId) o;
        return Objects.equals(song, that.song) && Objects.equals(playlist, that.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song, playlist);
    }
}
