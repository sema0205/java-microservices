package org.example.impl;

import lombok.AllArgsConstructor;
import org.example.OwnerService;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;


@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private OwnerDao ownerDao;

    @Override
    public void create(Owner owner) {
        ownerDao.create(owner);
    }

    @Override
    public void addCat(Owner owner, Cat cat) {
        Owner updatedOwner = ownerDao.getByName(owner.getName());
        updatedOwner.addCat(cat);
        ownerDao.update(updatedOwner);
    }

    @Override
    public void delete(Owner owner) {
        ownerDao.delete(owner);
    }

    @Override
    public Owner getByName(String name) {
        return ownerDao.getByName(name);
    }

    @Override
    public List<Owner> getAll() {
        return ownerDao.getAll();
    }
}
