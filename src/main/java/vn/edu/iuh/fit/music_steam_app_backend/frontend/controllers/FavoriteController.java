package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Favorite;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteService.add(favorite));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Favorite>> createFavorites(@RequestBody List<Favorite> favorites) {
        return ResponseEntity.ok(favoriteService.addMany(favorites));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable FavoriteId id, @RequestBody Favorite favorite) throws EntityIdNotFoundException {
        favorite.setId(id); // Assuming `Favorite` has an `id` field.
        return ResponseEntity.ok(favoriteService.update(favorite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long id) {
        try {
            favoriteService.delete(id);
            return ResponseEntity.ok("Favorite deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(favoriteService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Favorite not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        return ResponseEntity.ok((List<Favorite>) favoriteService.getAll().next());
    }
}
