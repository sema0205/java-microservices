package org.example.mapper;

import org.example.Dto.CatDto;
import org.example.Dto.OwnerDto;
import org.example.model.Cat;
import org.example.model.Owner;

public class OwnerMapper {

    public static Owner OwnerDtoToModel(OwnerDto ownerDto) {
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate(), ownerDto.getCats());
    }

    public static OwnerDto OwnerModelToDto(Owner owner) {

        if (owner == null) {
            return null;
        }

        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), owner.getCats());
    }

}
