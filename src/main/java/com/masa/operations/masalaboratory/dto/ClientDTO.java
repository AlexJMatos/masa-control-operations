package com.masa.operations.masalaboratory.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {
    private Long id;

    @NotEmpty(message = "El nombre no puede quedar vacio")
    @Size(max = 255, message = "El nombre del cliente no debe ser mayor a 255 caracteres")
    private String name;

    @Size(min = 12, max = 13, message = "El RFC debe contener 12 caracteres para personas morales y 13 para personas fisicas")
    private String rfc;

    @NotEmpty(message = "La dirección fiscal no puede quedar vacia")
    @Size(max = 255, message = "La dirección fiscal no debe ser mayor a 255 caracteres")
    private String fiscalAddress;
}
