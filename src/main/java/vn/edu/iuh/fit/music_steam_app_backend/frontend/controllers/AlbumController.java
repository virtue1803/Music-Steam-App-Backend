package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Album;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.add(album));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Album>> createAlbums(@RequestBody List<Album> albums) {
        return ResponseEntity.ok(albumService.addMany(albums));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) throws EntityIdNotFoundException {
        album.setId(id); // Assuming `Album` has an `id` field.
        return ResponseEntity.ok(albumService.update(album));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        try {
            albumService.delete(id);
            return ResponseEntity.ok("Album deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(albumService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Album not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok((List<Album>) albumService.getAll().next());
    }
}