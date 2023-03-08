package com.masa.operations.masalaboratory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String clientName;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "fiscal_address")
    private String fiscalAddress;

    @ElementCollection
    @OneToMany
    @JoinTable(name = "client_projects", joinColumns = {@JoinColumn(name = "client_id")},
    inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects = new HashSet<>();
}
