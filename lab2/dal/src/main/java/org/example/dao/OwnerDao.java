package org.example.dao;

import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface OwnerDao {

    void create(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    Owner getByName(String name);

    Owner getById(long id);

    List<Owner> getAll();

}
