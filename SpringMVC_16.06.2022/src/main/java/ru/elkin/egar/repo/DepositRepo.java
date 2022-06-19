package ru.elkin.egar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.egar.entity.Deposit;

import java.util.List;

public interface DepositRepo extends JpaRepository<Deposit, Long> {
    List<Deposit> findByName(String name);
    List<Deposit> findAllByOrderById();
}
