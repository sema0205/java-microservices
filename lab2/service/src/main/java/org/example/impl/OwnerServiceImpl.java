package org.example.impl;

import lombok.AllArgsConstructor;
import org.example.Dto.CatDto;
import org.example.Dto.OwnerDto;
import org.example.OwnerService;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.mapper.CatMapper;
import org.example.mapper.OwnerMapper;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private OwnerDao ownerDao;

    @Override
    public void create(OwnerDto ownerDto) {
        Owner owner = OwnerMapper.OwnerDtoToModel(ownerDto);

        ownerDao.create(owner);
    }

    @Override
    public void addCat(long ownerId, CatDto catDto) {
        Owner updatedOwner = ownerDao.getById(ownerId);
        Cat cat = CatMapper.CatDtoToModel(catDto);

        updatedOwner.addCat(cat);
        ownerDao.update(updatedOwner);
    }

    @Override
    public void delete(long ownerId) {
        Owner owner = ownerDao.getById(ownerId);
        ownerDao.delete(owner);
    }

    @Override
    public OwnerDto getByName(String name) {
        return OwnerMapper.OwnerModelToDto(ownerDao.getByName(name));
    }

    @Override
    public List<OwnerDto> getAll() {
        List<Owner> ownerList = ownerDao.getAll();
        List<OwnerDto> ownerDtoList = new ArrayList<>();

        for (Owner owner : ownerList) {
            ownerDtoList.add(OwnerMapper.OwnerModelToDto(owner));
        }

        return ownerDtoList;
    }
}
