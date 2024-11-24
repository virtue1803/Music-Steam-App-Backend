package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Playlist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.PlaylistRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService implements IServices<Playlist, Long> {
    
    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Playlist add(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> addMany(List<Playlist> list) {
        List<Playlist> results = new ArrayList<>();
        Iterator<Playlist> output = playlistRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Playlist update(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        playlistRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Playlist> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(playlistRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Playlist> getAll() {
        return playlistRepository.findAll().iterator();
    }
}
