package ru.elkin.egar;

import java.time.LocalDate;

/*
 * транзакции дохода
 */

public class TransactionIncomeDeposit {

    private Long id;
    private Long money;
    private LocalDate date;
    private Income income;
    private Deposit deposit;

    public TransactionIncomeDeposit(Income income, Deposit deposit) {
        this.income = income;
        this.deposit = deposit;
    }

    public void transactionMoney(){
        income.setFactMoney(income.getFactMoney() + money);
        deposit.setMoney(deposit.getMoney() + money);
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "TransactionIncomeDeposit{" +
                "id=" + id +
                ", money=" + money +
                ", date=" + date +
                ", income=" + income +
                ", deposit=" + deposit +
                '}';
    }
}
