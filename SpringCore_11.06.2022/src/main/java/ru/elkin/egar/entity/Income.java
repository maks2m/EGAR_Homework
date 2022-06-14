package ru.elkin.egar.entity;

/*
 * класс доходов
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
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // наименование получения дохода
    @Column(name = "name")
    private String name;
    // план получения денег
    @Column(name = "plan_money")
    private Long planMoney;
    // факт получения денег
    @Column(name = "fact_money")
    private Long factMoney;

}
