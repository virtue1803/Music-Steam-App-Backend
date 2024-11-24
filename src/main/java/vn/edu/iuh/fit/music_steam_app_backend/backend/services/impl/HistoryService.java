package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.History;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.HistoryRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService implements IServices<History, Long> {
    
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History add(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<History> addMany(List<History> list) {
        List<History> results = new ArrayList<>();
        Iterator<History> output = historyRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public History update(History history) {
        return historyRepository.save(history);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        historyRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<History> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(historyRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<History> getAll() {
        return historyRepository.findAll().iterator();
    }
}
