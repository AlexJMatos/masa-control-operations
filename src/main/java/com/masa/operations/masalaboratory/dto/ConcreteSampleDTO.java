package com.masa.operations.masalaboratory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConcreteSampleDTO {
    private String hourPlant;
    private String hourProject;
    private double realConcreteSlump;
    private double theoreticalConcreteSlump;
    private double temperature;
    private String section;
}
