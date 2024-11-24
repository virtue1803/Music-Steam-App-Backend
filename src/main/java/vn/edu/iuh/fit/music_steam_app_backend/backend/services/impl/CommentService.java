package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Comment;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.CommentRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements IServices<Comment, Long> {
    
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> addMany(List<Comment> list) {
        List<Comment> results = new ArrayList<>();
        Iterator<Comment> output = commentRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        commentRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Comment> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(commentRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Comment> getAll() {
        return commentRepository.findAll().iterator();
    }
}
