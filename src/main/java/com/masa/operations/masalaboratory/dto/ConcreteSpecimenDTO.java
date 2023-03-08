package com.masa.operations.masalaboratory.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConcreteSpecimenDTO {
    private String number;
    private int age;
    private LocalDate rehearsalDate;
}
