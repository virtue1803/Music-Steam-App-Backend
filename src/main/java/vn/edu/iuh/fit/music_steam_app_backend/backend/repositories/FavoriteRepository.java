package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Favorite;
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}