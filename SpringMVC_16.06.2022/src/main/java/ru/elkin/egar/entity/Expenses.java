package ru.elkin.egar.entity;

/*
 * класс расходов
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expenses implements Cloneable {

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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
