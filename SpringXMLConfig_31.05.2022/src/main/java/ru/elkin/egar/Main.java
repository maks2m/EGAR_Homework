package ru.elkin.egar;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

/*
 * программа учета финансов
 * деньги в копейках
 */

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        Income income = context.getBean("income", Income.class);
        Deposit deposit = context.getBean("deposit", Deposit.class);
        Expenses expenses = context.getBean("expenses", Expenses.class);

        TransactionIncomeDeposit transactionIncomeDeposit = context.getBean("transactionIncomeDeposit", TransactionIncomeDeposit.class);
        TransactionDepositExpenses transactionDepositExpenses = context.getBean("transactionDepositExpenses", TransactionDepositExpenses.class);

        System.out.println(income);
        System.out.println(deposit);
        System.out.println(expenses);
        System.out.println();

        transactionIncomeDeposit.setId(1L);
        transactionIncomeDeposit.setMoney(3000000L);
        transactionIncomeDeposit.setDate(LocalDate.now());
        transactionIncomeDeposit.transactionMoney();

        transactionDepositExpenses.setId(1L);
        transactionDepositExpenses.setMoney(200000L);
        transactionDepositExpenses.setDate(LocalDate.now());
        transactionDepositExpenses.transactionMoney();

        System.out.println(transactionIncomeDeposit);
        System.out.println(transactionDepositExpenses);
        System.out.println();

    }
}
