package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Rating;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.add(rating));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Rating>> createRatings(@RequestBody List<Rating> ratings) {
        return ResponseEntity.ok(ratingService.addMany(ratings));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) throws EntityIdNotFoundException {
        rating.setId(id); // Assuming `Rating` has an `id` field.
        return ResponseEntity.ok(ratingService.update(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        try {
            ratingService.delete(id);
            return ResponseEntity.ok("Rating deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ratingService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Rating not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok((List<Rating>) ratingService.getAll().next());
    }
}