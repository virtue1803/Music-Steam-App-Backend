package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Playlist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.PlaylistService;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.add(playlist));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Playlist>> createPlaylists(@RequestBody List<Playlist> playlists) {
        return ResponseEntity.ok(playlistService.addMany(playlists));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) throws EntityIdNotFoundException {
        playlist.setId(id); // Assuming `Playlist` has an `id` field.
        return ResponseEntity.ok(playlistService.update(playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id) {
        try {
            playlistService.delete(id);
            return ResponseEntity.ok("Playlist deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playlistService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Playlist not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return ResponseEntity.ok((List<Playlist>) playlistService.getAll().next());
    }
}
