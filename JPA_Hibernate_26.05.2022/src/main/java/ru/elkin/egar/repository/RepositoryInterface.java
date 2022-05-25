package ru.elkin.egar.repository;

import java.util.List;

public interface RepositoryInterface <T> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    void deleteById(Long id);
}
