package ru.elkin.egar.repo.customRepo;

import java.util.List;

public interface CustomRepo<T> {
    List<T> findAllTransactional();
}
