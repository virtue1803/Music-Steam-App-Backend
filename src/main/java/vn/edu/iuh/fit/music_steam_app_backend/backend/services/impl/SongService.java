package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Song;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.SongRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SongService implements IServices<Song, Long> {
    
    @Autowired
    private SongRepository songRepository;

    @Override
    public Song add(Song song) {
        return songRepository.save(song);
    }

    @Override
    public List<Song> addMany(List<Song> list) {
        List<Song> results = new ArrayList<>();
        Iterator<Song> output = songRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Song update(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        songRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Song> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(songRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Song> getAll() {
        return songRepository.findAll().iterator();
    }
}
