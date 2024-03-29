package org.example;

import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface OwnerService {

    void create(Owner owner);

    void addCat(Owner owner, Cat cat);

    void delete(Owner owner);

    Owner getByName(String name);

    List<Owner> getAll();

}
