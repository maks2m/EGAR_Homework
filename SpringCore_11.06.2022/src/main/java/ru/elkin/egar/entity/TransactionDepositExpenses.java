package ru.elkin.egar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/*
 * транзакции расхода
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "transaction_deposit_expenses")
public class TransactionDepositExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "money")
    private Long money;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deposit", foreignKey = @ForeignKey(name = "transaction_deposit_expenses_deposit_fk"))
    private Deposit deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expenses", foreignKey = @ForeignKey(name = "transaction_deposit_expenses_expenses_fk"))
    private Expenses expenses;

}
