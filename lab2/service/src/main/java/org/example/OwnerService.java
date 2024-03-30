package org.example;

import org.example.Dto.CatDto;
import org.example.Dto.OwnerDto;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface OwnerService {

    void create(OwnerDto ownerDto);

    void addCat(long ownerId, CatDto catDto);

    void delete(long ownerId);

    OwnerDto getByName(String name);

    List<OwnerDto> getAll();

}
