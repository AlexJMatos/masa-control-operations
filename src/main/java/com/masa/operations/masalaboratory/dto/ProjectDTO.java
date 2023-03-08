package com.masa.operations.masalaboratory.dto;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {
    private Long id;

    @Size(max = 255, message = "El nombre de la obra no puede quedar vacio")
    private String projectName;

    @Size(max = 255, message = "La dirección de la obra no puede quedar vacio")
    private String address;

    @Size(max = 255, message = "Favor de introducir el nombre del responsable de obra")
    private String resident;
}
