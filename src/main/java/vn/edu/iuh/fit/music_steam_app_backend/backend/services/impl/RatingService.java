package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Rating;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.RatingRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IServices<Rating, Long> {
    
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> addMany(List<Rating> list) {
        List<Rating> results = new ArrayList<>();
        Iterator<Rating> output = ratingRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        ratingRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Rating> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(ratingRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Rating> getAll() {
        return ratingRepository.findAll().iterator();
    }
}
