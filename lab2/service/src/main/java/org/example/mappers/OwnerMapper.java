package org.example.mappers;

import org.example.dto.OwnerDto;
import org.example.owner.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends Mappable<Owner, OwnerDto> {
}

