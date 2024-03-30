import org.example.CatService;
import org.example.Dto.CatDto;
import org.example.OwnerService;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.impl.CatServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CatServiceTest {

    private CatDao catDao = mock(CatDao.class);
    private OwnerDao ownerDao = mock(OwnerDao.class);

    @Test
    public void addFriendShouldAddFriendToCatAndSaveUpdatedCat() {

        Owner owner = new Owner();

        String ownerCatName = "Barsic";
        Cat ownerCat = new Cat();
        ownerCat.setId(1);
        ownerCat.setName(ownerCatName);
        ownerCat.setBirthDate(DateTime.parse("2020-01-25"));
        ownerCat.setBreed(Breed.MAINE_COON);
        ownerCat.setColor(Color.GRAY);

        CatDto friendCat = new CatDto();
        friendCat.setName("Mursic");
        friendCat.setBirthDate(DateTime.parse("2019-11-30"));
        friendCat.setBreed(Breed.SIAMESE);
        friendCat.setColor(Color.BLACK);

        Cat friendCatModel = CatMapper.CatDtoToModel(friendCat);

        CatService catService = new CatServiceImpl(catDao, ownerDao);

        when(catDao.getById(1)).thenReturn(ownerCat);

        catService.addFriend(1, friendCat);

        Assertions.assertTrue(ownerCat.getFriends().contains(friendCatModel));
        verify(catDao).getById(1);
        verify(catDao).update(ownerCat);
    }

    @Test
    public void deleteShouldRemoveCat() {

        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Murka");
        cat.setBirthDate(DateTime.parse("2018-05-20"));
        cat.setBreed(Breed.PERSIAN);
        cat.setColor(Color.WHITE);

        CatService catService = new CatServiceImpl(catDao, ownerDao);


        when(catDao.getById(cat.getId())).thenReturn(cat);
        catService.delete(cat.getId());

        verify(catDao).delete(cat);

        CatDto deletedCat = catService.getByName(cat.getName());
        assertNull(deletedCat);
    }

}
