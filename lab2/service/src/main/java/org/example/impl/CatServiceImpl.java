package org.example.impl;

import lombok.AllArgsConstructor;
import org.example.CatService;
import org.example.dao.CatDao;
import org.example.model.Cat;

import java.util.List;

@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private CatDao catDao;

    @Override
    public void create(Cat cat) {
        catDao.create(cat);
    }

    @Override
    public void changeOwner(Cat cat) {
        Cat updatedCat = catDao.getByName(cat.getName());
        updatedCat.setOwner(cat.getOwner());
        catDao.update(updatedCat);
    }

    @Override
    public void addFriend(Cat ownerCat, Cat friendCat) {
        Cat updatedCat = catDao.getByName(ownerCat.getName());
        updatedCat.addFriend(friendCat);
        catDao.update(updatedCat);
    }

    @Override
    public void delete(Cat cat) {
        catDao.delete(cat);
    }

    @Override
    public Cat getByName(String name) {
        return catDao.getByName(name);
    }

    @Override
    public List<Cat> getAll() {
        return catDao.getAll();
    }

}
