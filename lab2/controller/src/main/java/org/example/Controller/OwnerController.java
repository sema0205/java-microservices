package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.Dto.CatDto;
import org.example.Dto.OwnerDto;
import org.example.OwnerService;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;

    public void create(OwnerDto ownerDto) {
        Owner owner = ownerDto.ToModel();
        ownerService.create(owner);
    }

    public void delete(OwnerDto ownerDto) {
        Owner owner = ownerDto.ToModel();
        ownerService.delete(owner);
    }

    public void addCat(OwnerDto ownerDto, CatDto catDto) {
        Owner owner = ownerDto.ToModel();
        Cat cat = catDto.ToModel();
        ownerService.addCat(owner, cat);
    }

    public List<OwnerDto> getAll() {
        List<OwnerDto> ownersDto = new ArrayList<>();

        List<Owner> owners = ownerService.getAll();

        for (Owner owner : owners) {
            ownersDto.add(new OwnerDto(owner.getName(), owner.getBirthDate(), owner.getCats()));
        }

        return ownersDto;
    }

}
