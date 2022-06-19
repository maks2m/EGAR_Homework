package ru.elkin.egar.entity;

/*
 * класс денег в наличии
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "deposit")
public class Deposit implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // наименование хранения денег (наличность, банковская карта)
    @Column(name = "name")
    private String name;
    // текущие деньги
    @Column(name = "money")
    private Long money;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
