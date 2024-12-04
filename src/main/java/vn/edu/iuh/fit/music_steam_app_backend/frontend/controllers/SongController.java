package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.SongService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return ResponseEntity.ok(songService.add(song));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Song>> createSongs(@RequestBody List<Song> songs) {
        return ResponseEntity.ok(songService.addMany(songs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) throws EntityIdNotFoundException {
        song.setId(id); // Assuming `Song` has an `id` field.
        return ResponseEntity.ok(songService.update(song));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        try {
            songService.delete(id);
            return ResponseEntity.ok("Song deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(songService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Song not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }



    @GetMapping("/findByTitle")
    public ResponseEntity<Song> findSongByTitle(@RequestParam String title) {
        return songService.findSongByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSong();
        return songs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(songs);
    }
}
