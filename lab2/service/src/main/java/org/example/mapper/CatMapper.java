package org.example.mapper;

import org.example.Dto.CatDto;
import org.example.model.Cat;

public class CatMapper {

    public static Cat CatDtoToModel(CatDto catDto) {
        return new Cat(catDto.getId(), catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColor(), catDto.getOwner(), catDto.getFriends());
    }

    public static CatDto CatModelToDto(Cat cat) {

        if (cat == null) {
            return null;
        }

        return new CatDto(cat.getId(), cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), cat.getOwner(), cat.getFriends());
    }

}
