package org.example;

import org.example.cat.Color;
import org.example.dto.CatDto;

import java.util.List;

public interface CatService {

    CatDto create(
            CatDto cat
    );

    CatDto update(
            CatDto cat
    );

    void delete(
            Long id
    );

    CatDto addFriend(
            Long catId,
            Long friendId
    );

    CatDto getById(
            Long id
    );

    List<CatDto> getAllByColor(
            Color color
    );

    List<CatDto> getAll();

}

