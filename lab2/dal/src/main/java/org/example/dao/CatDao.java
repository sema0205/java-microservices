package org.example.dao;

import org.example.model.Cat;
import org.example.model.Owner;

import java.util.List;

public interface CatDao {

    void create(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

    Cat getByName(String name);

    Cat getById(long id);

    List<Cat> getAll();

}