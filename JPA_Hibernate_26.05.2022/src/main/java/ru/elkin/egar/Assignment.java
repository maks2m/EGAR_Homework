package ru.elkin.egar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "assignment")
@NamedQuery(
        name = "find_all_assignment",
        query = "SELECT a FROM Assignment a"
)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_assignment")
    private String numberAssignment;

    @Column(name = "date_assignment")
    private LocalDate dateAssignment;

    @Column(name = "name_npp")
    private String nameNpp;

    @Column(name = "number_block")
    private String numberBlock;

    @Column(name = "general_contractor")
    private String generalContractor;

    @Column(name = "number_contract")
    private String numberContract;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assignment")
    @ToString.Exclude
    private Set<ProductionOrder> productionOrders;

}
