package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}