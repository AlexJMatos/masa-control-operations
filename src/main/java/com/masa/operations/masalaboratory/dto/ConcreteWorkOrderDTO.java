package com.masa.operations.masalaboratory.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConcreteWorkOrderDTO {
    @NotEmpty(message = "La fecha de muestreo no puede quedar vacia")
    private String sampleDate;

    @NotEmpty(message = "La hora de servicio no puede quedar vacia")
    private String serviceHour;

    @Min(value = 50, message = "La resistencia no puede ser menor a 50 kgf")
    @Max(value = 600, message = "La resistencia no puede ser menor a 600 kgf")
    private int resistanceFc;

    @Positive(message = "El revenimiento no puede ser 0 o negativo")
    private double concreteSlump;

    @Positive(message = "El volumen no puede ser 0 o negativo")
    private double concreteVolume;

    @Positive(message = "El tma no puede ser 0 o negativo")
    private int tma;

    @Positive(message = "La edad de diseño no puede ser 0 o negativo")
    private int designAge;

    @NotEmpty(message = "El proveedor no puede quedar vacio")
    private String concreteProvider;
}