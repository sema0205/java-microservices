import org.example.CatService;
import org.example.OwnerService;
import org.example.dao.CatDao;
import org.example.impl.CatServiceImpl;
import org.example.impl.OwnerServiceImpl;
import org.example.model.Breed;
import org.example.model.Cat;
import org.example.model.Owner;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

public class CatServiceTest {

    private CatDao catDao = mock(CatDao.class);

    @Test
    public void addFriendShouldAddFriendToCatAndSaveUpdatedCat() {

        Owner owner = new Owner();

        String ownerCatName = "Barsic";
        Cat ownerCat = new Cat();
        ownerCat.setName(ownerCatName);
        ownerCat.setBirthDate(DateTime.parse("2020-01-25"));
        ownerCat.setBreed(Breed.MAINE_COON);
        ownerCat.setColor(Color.GRAY);

        Cat friendCat = new Cat();
        friendCat.setName("Mursic");
        friendCat.setBirthDate(DateTime.parse("2019-11-30"));
        friendCat.setBreed(Breed.SIAMESE);
        friendCat.setColor(Color.BLACK);

        CatService catService = new CatServiceImpl(catDao);

        when(catDao.getByName(ownerCatName)).thenReturn(ownerCat);

        catService.addFriend(ownerCat, friendCat);

        Assertions.assertTrue(ownerCat.getFriends().contains(friendCat));
        verify(catDao).getByName(ownerCatName);
        verify(catDao).update(ownerCat);
    }

    @Test
    public void deleteShouldRemoveCat() {

        Cat cat = new Cat();
        cat.setName("Мурка");
        cat.setBirthDate(DateTime.parse("2018-05-20"));
        cat.setBreed(Breed.PERSIAN);
        cat.setColor(Color.WHITE);

        CatService catService = new CatServiceImpl(catDao);

        catService.delete(cat);

        verify(catDao).delete(cat);
    }

}
