package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.Follow;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.FollowRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService implements IServices<Follow, Long> {
    
    @Autowired
    private FollowRepository followRepository;

    @Override
    public Follow add(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public List<Follow> addMany(List<Follow> list) {
        List<Follow> results = new ArrayList<>();
        Iterator<Follow> output = followRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public Follow update(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        followRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<Follow> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(followRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<Follow> getAll() {
        return followRepository.findAll().iterator();
    }
}
