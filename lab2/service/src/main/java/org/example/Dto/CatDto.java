package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Breed;
import org.example.model.Cat;
import org.example.model.Owner;
import org.joda.time.DateTime;

import java.awt.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {

    private long id;

    private String name;
    private DateTime birthDate;

    private Breed breed;
    private Color color;

    private Owner owner;

    private List<Cat> friends;

}


