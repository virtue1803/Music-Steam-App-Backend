package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Follow;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.FollowService;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping
    public ResponseEntity<Follow> createFollow(@RequestBody Follow follow) {
        return ResponseEntity.ok(followService.add(follow));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Follow>> createFollows(@RequestBody List<Follow> follows) {
        return ResponseEntity.ok(followService.addMany(follows));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Follow> updateFollow(@PathVariable Long id, @RequestBody Follow follow) throws EntityIdNotFoundException {
        follow.setId(id); // Assuming `Follow` has an `id` field.
        return ResponseEntity.ok(followService.update(follow));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFollow(@PathVariable Long id) {
        try {
            followService.delete(id);
            return ResponseEntity.ok("Follow deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Follow> getFollowById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(followService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Follow not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Follow>> getAllFollows() {
        return ResponseEntity.ok((List<Follow>) followService.getAll().next());
    }
}
