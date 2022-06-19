package ru.elkin.egar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.egar.entity.Income;

import java.util.List;

public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByName(String name);
    List<Income> findAllByOrderById();
}
