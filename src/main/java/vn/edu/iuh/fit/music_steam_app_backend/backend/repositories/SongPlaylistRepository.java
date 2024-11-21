package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.SongPlaylistId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.SongPlaylist;
@Repository
public interface SongPlaylistRepository extends JpaRepository<SongPlaylist, SongPlaylistId> {
}