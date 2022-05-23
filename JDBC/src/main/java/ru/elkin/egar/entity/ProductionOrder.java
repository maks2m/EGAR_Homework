package ru.elkin.egar.entity;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductionOrder {

    private long id;
    private String identifierProductionOrder;
    private String numberProductionOrder;
    private LocalDate dateProductionOrder;
    private String nameEquipment;
    private String manufacturer;
    private String numberDeveloperContract;
    private LocalDate dateDeveloperContract;
    private int numberCase;
    private String status;
    private String notes;
    private User responsibleUser;
    private Assignment assignment;

}
