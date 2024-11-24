package vn.edu.iuh.fit.music_steam_app_backend.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Comment;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.add(comment));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Comment>> createComments(@RequestBody List<Comment> comments) {
        return ResponseEntity.ok(commentService.addMany(comments));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) throws EntityIdNotFoundException {
        comment.setId(id); // Assuming `Comment` has an `id` field.
        return ResponseEntity.ok(commentService.update(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok("Comment deleted successfully.");
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(commentService.getById(id).orElseThrow(() -> new EntityIdNotFoundException("Comment not found")));
        } catch (EntityIdNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok((List<Comment>) commentService.getAll().next());
    }
}
