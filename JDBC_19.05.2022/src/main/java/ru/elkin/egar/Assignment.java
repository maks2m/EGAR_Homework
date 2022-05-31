package ru.elkin.egar;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Assignment {

    private long id;
    private String numberAssignment;
    private LocalDate dateAssignment;
    private String nameNpp;
    private String numberBlock;
    private String generalContractor;
    private String numberContract;
    private Set<ProductionOrder> productionOrders;

}
