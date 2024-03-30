package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.CatService;
import org.example.Dto.CatDto;
import org.example.Dto.OwnerDto;
import org.example.OwnerService;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CatController {

    private CatService catService;

    public void create(CatDto catDto) {
        catService.create(catDto);
    }

    public void delete(CatDto catDto) {
        catService.delete(catDto.getId());
    }

    public void changeOwner(long ownerId, CatDto catDto) {
        catService.changeOwner(ownerId, catDto.getId());
    }

    public void addFriend(long ownerId, CatDto catFriendDto) {
        catService.addFriend(ownerId, catFriendDto);
    }

    public List<CatDto> getAll() {
        return catService.getAll();
    }

}
