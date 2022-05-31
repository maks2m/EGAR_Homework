package ru.elkin.egar;

import java.time.LocalDate;

/*
 * транзакции расхода
 */

public class TransactionDepositExpenses {

    private Long id;
    private Long money;
    private LocalDate date;
    private Deposit deposit;
    private Expenses expenses;

    public void transactionMoney(){
        deposit.setMoney(deposit.getMoney() - money);
        expenses.setFactMoney(expenses.getFactMoney() + money);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "TransactionDepositExpenses{" +
                "id=" + id +
                ", money=" + money +
                ", date=" + date +
                ", deposit=" + deposit +
                ", expenses=" + expenses +
                '}';
    }
}
