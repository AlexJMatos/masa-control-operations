package com.masa.operations.masalaboratory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "concrete_specimens")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConcreteSpecimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "specimen_number")
    private String number;

    @Column(name = "specimen_age")
    private int age;

    @Column(name = "diameter_cm")
    private double diameter;

    @Column(name = "load_kgf")
    private double load;

    @Column(name = "failure_type")
    private FailureType failureType;

    @OneToOne
    @JoinTable(name = "concrete_sample_specimens", joinColumns = {@JoinColumn(name = "concrete_specimen_id")},
            inverseJoinColumns = {@JoinColumn(name = "concrete_sample_id")})
    private ConcreteSample concreteWorkOrder;

    public enum FailureType {
        TYPE_1("Tipo 1"),
        TYPE_2("Tipo 2"),
        TYPE_3("Tipo 3");

        private final String value;

        FailureType(String value){
            this.value = value;
        }
    }
}
