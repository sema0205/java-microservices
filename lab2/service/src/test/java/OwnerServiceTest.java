import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.example.Dto.OwnerDto;
import org.example.OwnerService;
import org.example.dao.OwnerDao;
import org.example.impl.OwnerServiceImpl;
import org.example.mapper.CatMapper;
import org.example.model.Breed;
import org.example.model.Cat;
import org.example.model.Owner;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {

    private OwnerDao ownerDao = mock(OwnerDao.class);

    @Test
    public void addCatShouldAddCatToOwnerAndSaveUpdatedOwner() {

        Owner owner = new Owner();

        String ownerName = "Lesha";
        owner.setId(1);
        owner.setName(ownerName);
        owner.setBirthDate(DateTime.parse("2000-06-15"));

        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setBirthDate(DateTime.parse("2020-01-25"));
        cat.setBreed(Breed.MAINE_COON);
        cat.setColor(Color.gray);

        OwnerService ownerService = new OwnerServiceImpl(ownerDao);

        when(ownerDao.getById(owner.getId())).thenReturn(owner);

        ownerService.addCat(owner.getId(), CatMapper.CatModelToDto(cat));

        Assertions.assertTrue(owner.getCats().contains(cat));
        verify(ownerDao).update(owner);
    }

    @Test
    public void deleteShouldRemoveOwner() {

        Owner owner = new Owner();

        String ownerName = "Lesha";
        owner.setId(1);
        owner.setName(ownerName);
        owner.setBirthDate(DateTime.parse("2000-06-15"));

        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setBirthDate(DateTime.parse("2020-01-25"));
        cat.setBreed(Breed.MAINE_COON);
        cat.setColor(Color.gray);

        OwnerService ownerService = new OwnerServiceImpl(ownerDao);

        when(ownerDao.getById(owner.getId())).thenReturn(owner);
        ownerService.delete(owner.getId());

        verify(ownerDao).delete(owner);

        OwnerDto deletedOwner = ownerService.getByName(owner.getName());
        assertNull(deletedOwner);
    }


}
