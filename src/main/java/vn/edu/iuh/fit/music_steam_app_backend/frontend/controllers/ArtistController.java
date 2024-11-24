package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Artist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return ResponseEntity.ok(artistService.add(artist));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Artist>> createArtists(@RequestBody List<Artist> artists) {
        return ResponseEntity.ok(artistService.addMany(artists));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artist) throws EntityIdNotFoundException {
        artist.setId(id); // Assuming `Artist` has an `id` field.
        return ResponseEntity.ok(artistService.update(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        try {
            artistService.delete(id);
            return ResponseEntity.ok("Artist deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(artistService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Artist not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok((List<Artist>) artistService.getAll().next());
    }
}
