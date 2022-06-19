package ru.elkin.egar.entity;

/*
 * класс доходов
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "income")
public class Income implements Cloneable {

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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
