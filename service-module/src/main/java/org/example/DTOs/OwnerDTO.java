package org.example.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OwnerDTO {
    private String name;
    private LocalDate birthDate;
}
