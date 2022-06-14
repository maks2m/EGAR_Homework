package ru.elkin.egar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.elkin.egar.entity.*;
import ru.elkin.egar.repo.*;
import ru.elkin.egar.service.TransactionDepositExpensesService;
import ru.elkin.egar.service.TransactionIncomeDepositService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("ru.elkin.egar");

        // ***** Repo ******
        IncomeRepo incomeRepo = context.getBean(IncomeRepo.class);
        DepositRepo depositRepo = context.getBean(DepositRepo.class);
        ExpensesRepo expensesRepo = context.getBean(ExpensesRepo.class);
        TransactionIncomeDepositRepo transactionIncomeDepositRepo = context.getBean(TransactionIncomeDepositRepo.class);
        TransactionDepositExpensesRepo transactionDepositExpensesRepo = context.getBean(TransactionDepositExpensesRepo.class);
        // *****************

        // ***** Service ******
        TransactionDepositExpensesService transactionDepositExpensesService = context.getBean(TransactionDepositExpensesService.class);
        TransactionIncomeDepositService transactionIncomeDepositService = context.getBean(TransactionIncomeDepositService.class);
        // ********************


        Set<PrototypeBeanClass> prototypeBeanClassList = new HashSet<>();
        for (int i = 0; i < 5; i++){
            prototypeBeanClassList.add(context.getBean(PrototypeBeanClass.class));
        }

        List<Income> incomesList = incomeRepo.findAll();
        List<Deposit> depositList = depositRepo.findAll();
        List<Expenses> expensesList = expensesRepo.findAll();

        List<TransactionIncomeDeposit> transactionIncomeDepositList = transactionIncomeDepositRepo.findAllTransaction();
        List<TransactionDepositExpenses> transactionDepositExpensesList = transactionDepositExpensesRepo.findAllTransactional();

        System.out.println(incomesList);
        System.out.println(depositList);
        System.out.println(expensesList);

        transactionIncomeDepositList.forEach(t -> {
            System.out.println( "TransactionIncomeDeposit{" +
                    "id=" + t.getId() +
                    ", money=" + t.getMoney() +
                    ", date=" + t.getDate() +
                    ", income=" + t.getIncome().getName() +
                    ", deposit=" + t.getDeposit().getName() +
                    '}'
            );
        });
        transactionDepositExpensesList.forEach(t -> {
            System.out.println( "transactionDepositExpenses{" +
                    "id=" + t.getId() +
                    ", money=" + t.getMoney() +
                    ", date=" + t.getDate() +
                    ", deposit=" + t.getDeposit().getName() +
                    ", expenses=" + t.getExpenses().getName() +
                    '}'
            );
        });


        TransactionIncomeDeposit tid = new TransactionIncomeDeposit(
                null,
                100000L,
                LocalDate.now(),
                incomeRepo.findByName("work").get(0),
                depositRepo.findByName("credit card").get(0)
        );
        transactionIncomeDepositService.transactionSave(tid);
        TransactionDepositExpenses tde = new TransactionDepositExpenses(
                null,
                100000L,
                LocalDate.now(),
                depositRepo.findByName("credit card").get(0),
                expensesRepo.findByName("auto").get(0)
        );
        transactionDepositExpensesService.transactionSave(tde);

        prototypeBeanClassList.forEach(System.out::println);
    }
}
