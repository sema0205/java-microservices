package org.example;

import org.example.Controller.CatController;
import org.example.Controller.OwnerController;
import org.example.Dto.CatDto;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.dao.impl.CatDaoImpl;
import org.example.dao.impl.OwnerDaoImpl;
import org.example.impl.CatServiceImpl;
import org.example.impl.OwnerServiceImpl;
import org.example.model.Breed;
import org.joda.time.DateTime;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        CatDao catDao = new CatDaoImpl();
        CatService catService = new CatServiceImpl(catDao);

        OwnerDao ownerDao = new OwnerDaoImpl();
        OwnerService ownerService = new OwnerServiceImpl(ownerDao);

        CatController catController = new CatController(catService);
        OwnerController ownerController = new OwnerController(ownerService);

        CatDto catDto = new CatDto();
        catDto.setName("Barik");
        catDto.setBirthDate(DateTime.now());
        catDto.setBreed(Breed.MAINE_COON);
        catDto.setColor(Color.red);

        catController.create(catDto);
    }
}