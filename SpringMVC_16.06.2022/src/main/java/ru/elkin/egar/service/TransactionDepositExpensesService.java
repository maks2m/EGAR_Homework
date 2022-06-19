package ru.elkin.egar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elkin.egar.entity.TransactionDepositExpenses;
import ru.elkin.egar.entity.TransactionIncomeDeposit;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.ExpensesRepo;
import ru.elkin.egar.repo.TransactionDepositExpensesRepo;

import java.util.Objects;

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

    @Transactional
    public void transactionEdit(TransactionDepositExpenses transactionDepositExpenses,
                                TransactionDepositExpenses oldTransactionDepositExpenses){

        if (Objects.equals(oldTransactionDepositExpenses.getDeposit().getId(), transactionDepositExpenses.getDeposit().getId())) {
            transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() + oldTransactionDepositExpenses.getMoney());
        } else {
            oldTransactionDepositExpenses.getDeposit().setMoney(oldTransactionDepositExpenses.getDeposit().getMoney() + oldTransactionDepositExpenses.getMoney());
            depositRepo.save(oldTransactionDepositExpenses.getDeposit());
        }

        if (Objects.equals(oldTransactionDepositExpenses.getExpenses().getId(), transactionDepositExpenses.getExpenses().getId())) {
            transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() - oldTransactionDepositExpenses.getMoney());
        } else {
            oldTransactionDepositExpenses.getExpenses().setFactMoney(oldTransactionDepositExpenses.getExpenses().getFactMoney() - oldTransactionDepositExpenses.getMoney());
            expensesRepo.save(oldTransactionDepositExpenses.getExpenses());
        }

        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() - transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() + transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }
    @Transactional
    public void transactionDelete(TransactionDepositExpenses transactionDepositExpenses) {
        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() + transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() - transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.delete(transactionDepositExpenses);
    }
}
