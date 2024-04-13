package org.example.impl;

import lombok.AllArgsConstructor;
import org.example.CatService;
import org.example.cat.Cat;
import org.example.cat.Color;
import org.example.dao.CatDao;
import org.example.dto.CatDto;
import org.example.exception.ResourceNotFoundException;
import org.example.mappers.CatMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatDao catDao;
    private final CatMapper catMapper;

    @Transactional
    @Cacheable(
            value = "CatService::getById",
            condition = "#cat.id!=null",
            key = "#cat.id"
    )
    public CatDto create(
            final CatDto cat
    ) {
        Cat catModel = catMapper.toModel(cat);
        Cat catResult = catDao.save(catModel);
        return catMapper.toDto(catResult);
    }

    @Override
    @Transactional
    @CachePut(
            value = "CatService::getById",
            key = "#cat.id"
    )
    public CatDto update(
            final CatDto cat
    ) {
        Cat existing = catDao.findById(cat.getId()).orElseThrow(() -> new ResourceNotFoundException("Cat not found."));

        existing.setOwner(cat.getOwner());
        existing.setName(cat.getName());
        existing.setBirthDate(cat.getBirthDate());
        existing.setBreed(cat.getBreed());
        existing.setColor(cat.getColor());
        existing.setFriendIds(cat.getFriendIds());

        Cat catResult = catDao.save(existing);
        return catMapper.toDto(catResult);
    }

    @Override
    @Transactional
    @CacheEvict(
            value = "CatService::getById",
            key = "#id"
    )
    public void delete(
            final Long id
    ) {
        catDao.deleteById(id);
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(
                    value = "CatService::getById",
                    condition = "#catId!=null",
                    key = "#catId"
            ),
            @Cacheable(
                    value = "CatService::getById",
                    condition = "#friendId!=null",
                    key = "#friendId"
            )
    })
    public CatDto addFriend(
            final Long catId,
            final Long friendId
    ) {
        Cat existing = catDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Cat not found."));
        Cat friend = catDao.findById(friendId).orElseThrow(() -> new ResourceNotFoundException("Cat not found."));

        catDao.addFriend(catId, friendId);
        Cat result = catDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Cat not found."));
        return catMapper.toDto(result);
    }

    @Override
    @Cacheable(
            value = "CatService::getById",
            key = "#id"
    )
    public CatDto getById(
            final Long id
    ) {
        return catMapper.toDto(catDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cat not found.")));
    }

    @Override
    @Cacheable(
            value = "CatService::getAllByColor",
            key = "#color"
    )
    public List<CatDto> getAllByColor(
            final Color color
    ) {
        List<Cat> cats = catDao.getAllByColor(color.toString());
        return catMapper.toDto(cats);
    }

    @Override
    @Cacheable(
            value = "CatService::getAll"
    )
    public List<CatDto> getAll() {
        List<Cat> allCats = catDao.findAll();
        return catMapper.toDto(allCats);
    }

}
