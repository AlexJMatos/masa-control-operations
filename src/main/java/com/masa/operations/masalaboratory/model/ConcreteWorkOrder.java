package com.masa.operations.masalaboratory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "concrete_work_orders")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConcreteWorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private String sheet;

    @Column(name = "sample_date")
    private LocalDate sampleDate;

    @Column(name = "service_hour")
    private LocalTime serviceHour;

    @Column(name = "resistance_fc_kgf_cm2") // kgf/cm2
    private int resistanceFc;

    @Column(name = "concrete_slump_cm") // cm
    private double concreteSlump;

    @Column(name = "concrete_volume_m3") // m3
    private double concreteVolume;

    @Column(name = "tma_mm") // mm
    private int tma;

    @Column(name = "design_age_days")
    private int designAge;

    @Column(name = "concrete_provider")
    private String concreteProvider;

    @OneToOne
    @JoinTable(name = "project_work_orders", joinColumns = {@JoinColumn(name = "concrete_work_order_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Project project;

    @ElementCollection
    @OneToMany
    @JoinTable(name = "concrete_work_order_samples", joinColumns = {@JoinColumn(name = "work_order_id")},
            inverseJoinColumns = {@JoinColumn(name = "sample_id")})
    private Set<ConcreteSample> concreteSamples = new HashSet<>();
}