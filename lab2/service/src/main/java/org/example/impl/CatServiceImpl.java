package org.example.impl;

import lombok.AllArgsConstructor;
import org.example.CatService;
import org.example.Dto.CatDto;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.mapper.CatMapper;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private CatDao catDao;
    private OwnerDao ownerDao;

    @Override
    public void create(CatDto catDto) {
        Cat cat = CatMapper.CatDtoToModel(catDto);
        catDao.create(cat);
    }

    @Override
    public void changeOwner(long ownerId, long catId) {
        Cat updatedCat = catDao.getById(catId);
        Owner owner = ownerDao.getById(ownerId);
        updatedCat.setOwner(owner);
        catDao.update(updatedCat);
    }

    @Override
    public void addFriend(long ownerCatId, CatDto friendCatDto) {
        Cat updatedCat = catDao.getById(ownerCatId);
        Cat friendCat = CatMapper.CatDtoToModel(friendCatDto);

        updatedCat.addFriend(friendCat);
        catDao.update(updatedCat);
    }

    @Override
    public void delete(long catId) {
        Cat cat = catDao.getById(catId);
        catDao.delete(cat);
    }

    @Override
    public CatDto getByName(String name) {
        Cat cat = catDao.getByName(name);
        return CatMapper.CatModelToDto(cat);
    }

    @Override
    public List<CatDto> getAll() {
        List<Cat> catList = catDao.getAll();
        List<CatDto> catDtosList = new ArrayList<>();

        for (Cat cat : catList) {
            catDtosList.add(CatMapper.CatModelToDto(cat));
        }

        return catDtosList;
    }

}
