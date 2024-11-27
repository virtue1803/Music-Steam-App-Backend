package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.SongPlaylistId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.SongPlaylist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.SongPlaylistService;

import java.util.List;

@RestController
@RequestMapping("/api/song-playlists")
public class SongPlaylistController {

    @Autowired
    private SongPlaylistService songPlaylistService;

    @PostMapping
    public ResponseEntity<SongPlaylist> createSongPlaylist(@RequestBody SongPlaylist songPlaylist) {
        return ResponseEntity.ok(songPlaylistService.add(songPlaylist));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<SongPlaylist>> createSongPlaylists(@RequestBody List<SongPlaylist> songPlaylists) {
        return ResponseEntity.ok(songPlaylistService.addMany(songPlaylists));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongPlaylist> updateSongPlaylist(@PathVariable SongPlaylistId id, @RequestBody SongPlaylist songPlaylist) throws EntityIdNotFoundException {
        songPlaylist.setId(id); // Assuming `SongPlaylist` has an `id` field.
        return ResponseEntity.ok(songPlaylistService.update(songPlaylist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSongPlaylist(@PathVariable SongPlaylistId id) {
        try {
            songPlaylistService.delete(id);
            return ResponseEntity.ok("SongPlaylist deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongPlaylist> getSongPlaylistById(@PathVariable SongPlaylistId id) {
        try {
            return ResponseEntity.ok(songPlaylistService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("SongPlaylist not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<SongPlaylist>> getAllSongPlaylists() {
        return ResponseEntity.ok((List<SongPlaylist>) songPlaylistService.getAll().next());
    }
}
