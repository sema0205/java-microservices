package org.example.impl;

import lombok.RequiredArgsConstructor;
import org.example.OwnerService;
import org.example.cat.Cat;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.dto.OwnerDto;
import org.example.exception.ResourceNotFoundException;
import org.example.mappers.CatMapper;
import org.example.mappers.OwnerMapper;
import org.example.owner.Owner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;
    private final CatDao catDao;
    private final OwnerMapper ownerMapper;
    private final CatMapper catMapper;

    @Transactional
    @Cacheable(
            value = "OwnerService::getById",
            condition = "#owner.id!=null",
            key = "#owner.id"
    )
    public OwnerDto create(
            final OwnerDto owner
    ) {
        Owner ownerModel = ownerMapper.toModel(owner);
        Owner ownerResult = ownerDao.save(ownerModel);
        return ownerMapper.toDto(ownerResult);
    }

    @Override
    @Transactional
    @CachePut(
            value = "OwnerService::getById",
            key = "#owner.id"
    )
    public OwnerDto update(
            final OwnerDto owner
    ) {
        Owner existing = ownerDao.findById(owner.getId()).orElseThrow(() -> new ResourceNotFoundException("Owner not found."));

        existing.setName(owner.getName());
        existing.setBirthDate(owner.getBirthDate());
        existing.setCatIds(owner.getCatIds());

        Owner ownerResult = ownerDao.save(existing);
        return ownerMapper.toDto(ownerResult);
    }

    @Override
    @Transactional
    @CacheEvict(
            value = "OwnerService::getById",
            key = "#id"
    )
    public void delete(
            final Long id
    ) {
        ownerDao.deleteById(id);
    }

    @Override
    @Transactional
    @CachePut(
            value = "OwnerService::getById",
            key = "#ownerId"
    )
    public OwnerDto addCat(
            final Long ownerId,
            final Long catId
    ) {
        Owner existing = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found."));
        Cat cat = catDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Cat not found."));


        ownerDao.addCat(ownerId, catId);
        Owner result = ownerDao.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found."));
        return ownerMapper.toDto(result);
    }

    @Override
    @Cacheable(
            value = "OwnerService::getById",
            key = "#id"
    )
    public OwnerDto getById(
            final Long id
    ) {
        return ownerMapper.toDto(ownerDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Owner not found.")));
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(
                    value = "OwnerService::getAllByBirthDateRange",
                    key = "#start"
            ),
            @Cacheable(
                    value = "OwnerService::getAllByBirthDateRange",
                    key = "#end"
            )
    })
    public List<OwnerDto> getAllByBirthDateRange(
            final Timestamp start,
            final Timestamp end
    ) {
        List<Owner> owners = ownerDao.getAllByBirthDateRange(start, end);

        return ownerMapper.toDto(owners);
    }

    @Override
    @Cacheable(
            value = "OwnerService::getAll"
    )
    public List<OwnerDto> getAll() {
        List<Owner> allOwners = ownerDao.findAll();
        return ownerMapper.toDto(allOwners);
    }

}
