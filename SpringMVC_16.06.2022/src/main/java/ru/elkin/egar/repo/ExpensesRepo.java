package ru.elkin.egar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.egar.entity.Expenses;

import java.util.List;

public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
    List<Expenses> findByName(String name);
    List<Expenses> findAllByOrderById();
}
