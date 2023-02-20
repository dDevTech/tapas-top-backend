package dev.gpi.g5.tapastopbackend.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T object);

    void delete(T object);

    void deleteById(Long id);

}