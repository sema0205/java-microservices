import org.example.OwnerService;
import org.example.cat.Breed;
import org.example.cat.Cat;
import org.example.cat.Color;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.dto.CatDto;
import org.example.dto.OwnerDto;
import org.example.impl.OwnerServiceImpl;
import org.example.mappers.CatMapper;
import org.example.mappers.CatMapperImpl;
import org.example.mappers.OwnerMapper;
import org.example.mappers.OwnerMapperImpl;
import org.example.owner.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {

    private final OwnerDao ownerDao = mock(OwnerDao.class);
    private final CatDao catDao = mock(CatDao.class);
    private final CatMapper catMapper = new CatMapperImpl();
    private final OwnerMapper ownerMapper = new OwnerMapperImpl();

    @Test
    public void addCatShouldAddCatToOwnerAndSaveUpdatedOwner() {

        Owner owner = new Owner();

        String ownerName = "Lesha";
        owner.setId(1L);
        owner.setName(ownerName);
        owner.setCats(new ArrayList<>());
        owner.setBirthDate(LocalDateTime.parse("2000-06-15T00:00"));

        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Barsik");
        cat.setFriends(new ArrayList<>());
        cat.setBirthDate(LocalDateTime.parse("2020-01-25T00:00"));
        cat.setBreed(Breed.MAINE_COON);
        cat.setColor(Color.GRAY);

        CatDto catDto = catMapper.toDto(cat);

        OwnerService ownerService = new OwnerServiceImpl(ownerDao, catDao, ownerMapper, catMapper);

        when(ownerDao.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(catDao.findById(cat.getId())).thenReturn(Optional.of(cat));
        when(ownerDao.save(owner)).thenReturn(owner);

        ownerService.addCat(owner.getId(), catDto.getId());

        Assertions.assertTrue(owner.getCats().contains(cat));
        verify(ownerDao).save(owner);
    }

    @Test
    public void deleteShouldRemoveOwner() {

        Owner owner = new Owner();

        String ownerName = "Lesha";
        owner.setId(1L);
        owner.setName(ownerName);
        owner.setBirthDate(LocalDateTime.parse("2000-06-15T00:00"));

        OwnerService ownerService = new OwnerServiceImpl(ownerDao, catDao, ownerMapper, catMapper);

        when(ownerDao.findById(owner.getId())).thenReturn(Optional.of(owner));
        ownerService.delete(owner.getId());

        verify(ownerDao).deleteById(owner.getId());

        OwnerDto deletedOwner = ownerService.getById(owner.getId());
        assertEquals(owner.getId(), deletedOwner.getId());
    }


}
