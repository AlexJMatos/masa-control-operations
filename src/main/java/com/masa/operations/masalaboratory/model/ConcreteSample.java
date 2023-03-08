package com.masa.operations.masalaboratory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "concrete_specimens")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConcreteSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hour_plant")
    private LocalTime hourPlant;

    @Column(name = "hour_project")
    private LocalTime hourProject;

    @Column(name = "sample_number")
    private int sampleNumber;

    @Column(name = "real_concrete_slump")
    private double realConcreteSlump;

    @Column(name = "theoretical_concrete_slump")
    private double theoreticalConcreteSlump;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "section")
    private String section;

    @OneToOne
    @JoinTable(name = "concrete_work_order_samples", joinColumns = {@JoinColumn(name = "concrete_sample_id")},
            inverseJoinColumns = {@JoinColumn(name = "concrete_work_order_id")})
    private ConcreteWorkOrder concreteWorkOrder;

    @ElementCollection
    @OneToMany
    @JoinTable(name = "concrete_sample_specimens", joinColumns = {@JoinColumn(name = "concrete_sample_id")},
            inverseJoinColumns = {@JoinColumn(name = "concrete_specimen_id")})
    private List<ConcreteSpecimen> specimens;
}
