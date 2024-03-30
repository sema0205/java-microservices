package org.example;

import org.example.Dto.CatDto;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface CatService {

    void create(CatDto catDto);

    void changeOwner(long ownerId, long catId);

    void addFriend(long ownerCatId, CatDto friendCatDto);

    void delete(long catId);

    CatDto getByName(String name);

    List<CatDto> getAll();

}
