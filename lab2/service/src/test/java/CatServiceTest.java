import org.example.CatService;
import org.example.cat.Breed;
import org.example.cat.Cat;
import org.example.cat.Color;
import org.example.dao.CatDao;
import org.example.dto.CatDto;
import org.example.impl.CatServiceImpl;
import org.example.mappers.CatMapper;
import org.example.mappers.CatMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatServiceTest {

    private final CatDao catDao = mock(CatDao.class);
    private final CatMapper catMapper = new CatMapperImpl();

    @Test
    public void addFriendShouldAddFriendToCatAndSaveUpdatedCat() {

        String ownerCatName = "Barsic";
        Cat ownerCat = new Cat();
        ownerCat.setId(1L);
        ownerCat.setName(ownerCatName);
        ownerCat.setFriendIds(new ArrayList<>());
        ownerCat.setBirthDate(LocalDateTime.parse("2020-01-25T00:00"));
        ownerCat.setBreed(Breed.MAINE_COON);
        ownerCat.setColor(org.example.cat.Color.GRAY);

        CatDto friendCat = new CatDto();
        friendCat.setId(2L);
        friendCat.setName("Mursic");
        friendCat.setFriendIds(new ArrayList<>());
        friendCat.setBirthDate(LocalDateTime.parse("2020-01-25T00:00"));
        friendCat.setBreed(Breed.SIAMESE);
        friendCat.setColor(org.example.cat.Color.BLACK);

        Cat friendCatModel = catMapper.toModel(friendCat);
        CatDto ownerDto = catMapper.toDto(ownerCat);

        CatService catService = new CatServiceImpl(catDao, catMapper);

        when(catDao.findById(ownerCat.getId())).thenReturn(Optional.of(ownerCat));
        when(catDao.findById(friendCatModel.getId())).thenReturn(Optional.of(friendCatModel));
        when(catDao.save(ownerCat)).thenReturn(ownerCat);

        catService.addFriend(ownerCat.getId(), friendCatModel.getId());

        verify(catDao).addFriend(ownerCat.getId(), friendCatModel.getId());
    }

    @Test
    public void deleteShouldRemoveCat() {

        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Murka");
        cat.setBirthDate(LocalDateTime.parse("2018-05-20T00:00"));
        cat.setBreed(Breed.PERSIAN);
        cat.setColor(Color.WHITE);

        CatService catService = new CatServiceImpl(catDao, catMapper);

        when(catDao.findById(cat.getId())).thenReturn(Optional.of(cat));
        catService.delete(cat.getId());

        verify(catDao).deleteById(cat.getId());

        CatDto deletedCat = catService.getById(cat.getId());
        assertEquals(cat.getId(), deletedCat.getId());
    }

}
