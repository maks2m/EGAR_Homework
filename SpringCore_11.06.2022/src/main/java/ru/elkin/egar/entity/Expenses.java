package ru.elkin.egar.entity;

/*
 * класс расходов
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Component
@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // имя расходов (авто, еда, спиртное)
    @Column(name = "name")
    private String name;
    // план потраченных денег
    @Column(name = "plan_money")
    private Long planMoney;
    // факт потраченных денег
    @Column(name = "fact_money")
    private Long factMoney;

}
