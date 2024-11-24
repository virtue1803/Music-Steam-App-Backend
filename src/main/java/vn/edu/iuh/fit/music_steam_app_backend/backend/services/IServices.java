package vn.edu.iuh.fit.music_steam_app_backend.backend.services;

import vn.edu.iuh.fit.music_steam_app_backend.backend.exceptions.EntityIdNotFoundException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface IServices<T, P> {
    T add(T t);
    List<T> addMany(List<T> list);
    T update(T t);
    void delete(P p) throws EntityIdNotFoundException;
    Optional<T> getById(P p) throws EntityIdNotFoundException;
    Iterator<T> getAll();
}
