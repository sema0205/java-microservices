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
        Cat cat = catDto.ToModel();
        catService.create(cat);
    }

    public void delete(CatDto catDto) {
        Cat cat = catDto.ToModel();
        catService.delete(cat);
    }

    public void changeOwner(CatDto catDto) {
        Cat cat = catDto.ToModel();
        catService.changeOwner(cat);
    }

    public void addFriend(CatDto catOwnerDto, CatDto catFriendDto) {
        Cat catOwner = catOwnerDto.ToModel();
        Cat catFriend = catFriendDto.ToModel();
        catService.addFriend(catOwner, catFriend);
    }

    public List<CatDto> getAll() {
        List<CatDto> catsDto = new ArrayList<>();

        List<Cat> cats = catService.getAll();

        for (Cat cat : cats) {
            catsDto.add(new CatDto(
                    cat.getName(),
                    cat.getBirthDate(),
                    cat.getBreed(),
                    cat.getColor(),
                    cat.getOwner(),
                    cat.getFriends())
            );
        }

        return catsDto;
    }

}
