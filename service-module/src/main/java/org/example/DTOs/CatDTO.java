package org.example.DTOs;

import lombok.Data;
import org.example.Enums.Color;


import java.time.LocalDate;
import java.util.List;

@Data
public class CatDTO {
    private OwnerDTO owner;
    private String name;
    private LocalDate birthdate;
    private String breed;
    private Color color;
    private List<CatDTO> friends;
}
