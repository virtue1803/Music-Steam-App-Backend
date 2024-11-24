package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.History;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/histories")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        return ResponseEntity.ok(historyService.add(history));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<History>> createHistories(@RequestBody List<History> histories) {
        return ResponseEntity.ok(historyService.addMany(histories));
    }

    @PutMapping("/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable Long id, @RequestBody History history) throws EntityIdNotFoundException {
        history.setId(id); // Assuming `History` has an `id` field.
        return ResponseEntity.ok(historyService.update(history));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable Long id) {
        try {
            historyService.delete(id);
            return ResponseEntity.ok("History deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(historyService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("History not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<History>> getAllHistories() {
        return ResponseEntity.ok((List<History>) historyService.getAll().next());
    }
}