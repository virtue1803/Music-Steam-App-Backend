package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        return ResponseEntity.ok(userService.addMany(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws EntityIdNotFoundException {
        user.setId(id); // Assuming `User` has an `id` field.
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("User not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok((List<User>) userService.getAll().next());
    }
}