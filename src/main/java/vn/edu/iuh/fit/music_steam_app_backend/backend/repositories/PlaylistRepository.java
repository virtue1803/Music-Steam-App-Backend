package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}