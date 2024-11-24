package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Artist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.ArtistRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements IServices<Artist, Long> {
    
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist add(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public List<Artist> addMany(List<Artist> list) {
        List<Artist> results = new ArrayList<>();
        Iterator<Artist> output = artistRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Artist update(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        artistRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Artist> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(artistRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Artist> getAll() {
        return artistRepository.findAll().iterator();
    }
}
