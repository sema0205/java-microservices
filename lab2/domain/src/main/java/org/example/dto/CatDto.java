package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.cat.Breed;
import org.example.cat.Color;
import org.example.owner.Owner;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CatDto {

    private Long id;

    private String name;

    private LocalDateTime birthDate;

    private Breed breed;

    private Color color;

    private Owner owner;

    private List<Long> friendIds;

}
