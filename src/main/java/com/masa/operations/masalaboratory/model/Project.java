package com.masa.operations.masalaboratory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "address")
    private String address;

    @Column(name = "resident")
    private String resident;

    @OneToOne
    @JoinTable(name = "client_projects", joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private Client client;

    @ElementCollection
    @OneToMany
    @JoinTable(name = "project_work_orders", joinColumns = {@JoinColumn(name = "project_id")},
    inverseJoinColumns = {@JoinColumn(name = "concrete_work_order_id")})
    private Set<ConcreteWorkOrder> concreteWorkOrders = new HashSet<>();
}