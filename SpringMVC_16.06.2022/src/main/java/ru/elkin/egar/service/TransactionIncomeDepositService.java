package ru.elkin.egar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elkin.egar.entity.TransactionIncomeDeposit;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.IncomeRepo;
import ru.elkin.egar.repo.TransactionIncomeDepositRepo;

import java.util.Objects;

@Service
public class TransactionIncomeDepositService {

    private final DepositRepo depositRepo;
    private final IncomeRepo incomeRepo;
    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;

    @Autowired
    public TransactionIncomeDepositService(DepositRepo depositRepo,
                                           IncomeRepo incomeRepo,
                                           TransactionIncomeDepositRepo transactionIncomeDepositRepo) {
        this.depositRepo = depositRepo;
        this.incomeRepo = incomeRepo;
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
    }

    @Transactional
    public void transactionSave(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() + transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() + transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }

    @Transactional
    public void transactionEdit(TransactionIncomeDeposit transactionIncomeDeposit,
                                TransactionIncomeDeposit oldTransactionIncomeDeposit){

        if (Objects.equals(oldTransactionIncomeDeposit.getIncome().getId(), transactionIncomeDeposit.getIncome().getId())) {
            transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() - oldTransactionIncomeDeposit.getMoney());
        } else {
            oldTransactionIncomeDeposit.getIncome().setFactMoney(oldTransactionIncomeDeposit.getIncome().getFactMoney() - oldTransactionIncomeDeposit.getMoney());
            incomeRepo.save(oldTransactionIncomeDeposit.getIncome());
        }

        if (Objects.equals(oldTransactionIncomeDeposit.getDeposit().getId(), transactionIncomeDeposit.getDeposit().getId())) {
            transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() - oldTransactionIncomeDeposit.getMoney());
        } else {
            oldTransactionIncomeDeposit.getDeposit().setMoney(oldTransactionIncomeDeposit.getDeposit().getMoney() - oldTransactionIncomeDeposit.getMoney());
            depositRepo.save(oldTransactionIncomeDeposit.getDeposit());
        }

        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() + transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() + transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }

    public void transactionDelete(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() - transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() - transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.delete(transactionIncomeDeposit);
    }

}
