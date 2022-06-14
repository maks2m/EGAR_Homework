package ru.elkin.egar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.egar.entity.TransactionDepositExpenses;
import ru.elkin.egar.repo.customRepo.CustomRepo;

public interface TransactionDepositExpensesRepo extends JpaRepository<TransactionDepositExpenses, Long>, CustomRepo<TransactionDepositExpenses> {
}
