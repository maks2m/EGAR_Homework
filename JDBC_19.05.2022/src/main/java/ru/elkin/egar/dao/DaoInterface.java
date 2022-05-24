package ru.elkin.egar.dao;

import java.util.List;

public interface DaoInterface<T> {

    List<T> findAll();
    T findById(Long id);
    void create(T model);
    void update(Long id, T model);
    void delete(Long id);

}
