package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Favorite;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {

    Optional<Favorite> findById(FavoriteId favoriteId);

    Optional<Favorite> findByUserAndSong(User user, Song song);


}