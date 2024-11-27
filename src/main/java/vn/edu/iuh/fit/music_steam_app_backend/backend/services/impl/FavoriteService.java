package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.ids.FavoriteId;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Favorite;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.FavoriteRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService implements IServices<Favorite, FavoriteId> {
    
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public Favorite add(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> addMany(List<Favorite> list) {
        List<Favorite> results = new ArrayList<>();
        Iterator<Favorite> output = favoriteRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Favorite update(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void delete(FavoriteId id) throws EntityIdNotFoundException {
        favoriteRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Favorite> getById(FavoriteId aLong) throws EntityIdNotFoundException {
        return Optional.of(favoriteRepository.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(aLong + "")));
    }




    @Override
    public Iterator<Favorite> getAll() {
        return favoriteRepository.findAll().iterator();
    }
}
