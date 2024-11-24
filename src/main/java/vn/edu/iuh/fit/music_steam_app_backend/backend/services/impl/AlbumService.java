package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Album;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.AlbumRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IServices<Album, Long> {
    
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album add(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List<Album> addMany(List<Album> list) {
        List<Album> results = new ArrayList<>();
        Iterator<Album> output = albumRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Album update(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        albumRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Album> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(albumRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Album> getAll() {
        return albumRepository.findAll().iterator();
    }
}
