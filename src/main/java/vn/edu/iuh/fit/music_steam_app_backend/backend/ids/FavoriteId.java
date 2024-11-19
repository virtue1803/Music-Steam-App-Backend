package vn.edu.iuh.fit.music_steam_app_backend.backend.ids;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteId)) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(user, that.user) && Objects.equals(song, that.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, song);
    }
}
