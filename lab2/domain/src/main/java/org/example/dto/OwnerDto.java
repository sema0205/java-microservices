package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OwnerDto {

    private Long id;

    private String name;

    private LocalDateTime birthDate;

    private List<CatDto> cats;

}
