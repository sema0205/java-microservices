package org.example;

import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface CatService {

    void create(Cat cat);

    void changeOwner(Cat cat);

    void addFriend(Cat ownerCat, Cat friendCat);

    void delete(Cat cat);

    Cat getByName(String name);

    List<Cat> getAll();

}
