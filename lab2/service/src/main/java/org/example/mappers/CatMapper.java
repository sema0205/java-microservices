package org.example.mappers;

import org.example.cat.Cat;
import org.example.dto.CatDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatMapper extends Mappable<Cat, CatDto> {
}

