package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.SongPlaylistId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.SongPlaylist;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.SongPlaylistRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SongPlaylistService implements IServices<SongPlaylist, SongPlaylistId> {
    
    @Autowired
    private SongPlaylistRepository songPlaylistRepository;

    @Override
    public SongPlaylist add(SongPlaylist songPlaylist) {
        return songPlaylistRepository.save(songPlaylist);
    }

    @Override
    public List<SongPlaylist> addMany(List<SongPlaylist> list) {
        List<SongPlaylist> results = new ArrayList<>();
        Iterator<SongPlaylist> output = songPlaylistRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public SongPlaylist update(SongPlaylist songPlaylist) {
        return songPlaylistRepository.save(songPlaylist);
    }

    @Override
    public void delete(SongPlaylistId id) throws EntityIdNotFoundException {
        songPlaylistRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<SongPlaylist> getById(SongPlaylistId id) throws EntityIdNotFoundException {
        return Optional.of(songPlaylistRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<SongPlaylist> getAll() {
        return songPlaylistRepository.findAll().iterator();
    }
}
