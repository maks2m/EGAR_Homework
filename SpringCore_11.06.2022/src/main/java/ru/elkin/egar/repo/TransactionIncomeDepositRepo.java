package ru.elkin.egar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.elkin.egar.entity.TransactionIncomeDeposit;

import java.util.List;

public interface TransactionIncomeDepositRepo extends JpaRepository<TransactionIncomeDeposit, Long> {
    @Query("select t from TransactionIncomeDeposit t join fetch t.income join fetch t.deposit")
    List<TransactionIncomeDeposit> findAllTransaction();
}
