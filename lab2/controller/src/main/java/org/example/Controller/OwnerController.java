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
        ownerService.create(ownerDto);
    }

    public void delete(OwnerDto ownerDto) {
        ownerService.delete(ownerDto.getId());
    }

    public void addCat(long ownerId, CatDto catDto) {
        ownerService.addCat(ownerId, catDto);
    }

    public List<OwnerDto> getAll() {
        return ownerService.getAll();
    }

}
