package ru.elkin.egar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elkin.egar.entity.TransactionDepositExpenses;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.ExpensesRepo;
import ru.elkin.egar.repo.TransactionDepositExpensesRepo;

@Service
public class TransactionDepositExpensesService {

    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;

    @Autowired
    public TransactionDepositExpensesService(DepositRepo depositRepo,
                                             ExpensesRepo expensesRepo,
                                             TransactionDepositExpensesRepo transactionDepositExpensesRepo) {
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
    }

    @Transactional
    public void transactionSave(TransactionDepositExpenses transactionDepositExpenses){
        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() - transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() + transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }

}
