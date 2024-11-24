package vn.edu.iuh.fit.music_steam_app_backend.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.music_steam_app_backend.backend.models.User;
import vn.edu.iuh.fit.music_steam_app_backend.backend.repositories.UserRepository;
import vn.edu.iuh.fit.music_steam_app_backend.backend.services.IServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IServices<User, Long> {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> addMany(List<User> list) {
        List<User> results = new ArrayList<>();
        Iterator<User> output = userRepository.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws EntityIdNotFoundException {
        userRepository.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Optional<User> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    @Override
    public Iterator<User> getAll() {
        return userRepository.findAll().iterator();
    }
}
