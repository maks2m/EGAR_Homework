package ru.elkin.egar.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "production_order")
@NamedQuery(
        name = "find_all_production_order",
        query = "SELECT p FROM ProductionOrder p"
)
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identifier_production_order")
    private String identifierProductionOrder;

    @Column(name = "number_production_order")
    private String numberProductionOrder;

    @Column(name = "date_production_order")
    private LocalDate dateProductionOrder;

    @Column(name = "name_equipment")
    private String nameEquipment;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "number_developer_contract")
    private String numberDeveloperContract;

    @Column(name = "date_developer_contract")
    private LocalDate dateDeveloperContract;

    @Column(name = "number_case")
    private int numberCase;

    @Column(name = "status")
    private String status;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsible_user")
    @ToString.Exclude
    private User responsibleUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_assignment")
    @ToString.Exclude
    private Assignment assignment;

}
