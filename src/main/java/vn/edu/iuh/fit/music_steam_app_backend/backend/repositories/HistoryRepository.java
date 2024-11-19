package vn.edu.iuh.fit.music_steam_app_backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}