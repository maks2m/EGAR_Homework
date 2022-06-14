package ru.elkin.egar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elkin.egar.entity.TransactionIncomeDeposit;
import ru.elkin.egar.repo.DepositRepo;
import ru.elkin.egar.repo.IncomeRepo;
import ru.elkin.egar.repo.TransactionIncomeDepositRepo;

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


}
