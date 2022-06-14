package ru.elkin.egar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/*
 * транзакции дохода
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "transaction_income_deposit")
public class TransactionIncomeDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "money")
    private Long money;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_income", foreignKey = @ForeignKey(name = "transaction_income_deposit_income_fk"))
    private Income income;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deposit", foreignKey = @ForeignKey(name = "transaction_income_deposit_deposit_fk"))
    private Deposit deposit;

}
