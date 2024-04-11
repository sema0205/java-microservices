package org.example.impl;

import lombok.RequiredArgsConstructor;
import org.example.CatService;
import org.example.OwnerService;
import org.example.cat.Cat;
import org.example.dao.CatDao;
import org.example.dao.OwnerDao;
import org.example.dto.CatDto;
import org.example.dto.OwnerDto;
import org.example.exception.ResourceNotFoundException;
import org.example.mappers.CatMapper;
import org.example.mappers.OwnerMapper;
import org.example.owner.Owner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        existing.setCats(catMapper.toModel(owner.getCats()));

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

        existing.getCats().add(cat);

        Owner ownerResult = ownerDao.save(existing);
        return ownerMapper.toDto(ownerResult);
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
    @Cacheable(
            value = "OwnerService::getAllByBirthDateRange",
            key = "#duration"
    )
    public List<OwnerDto> getAllByBirthDateRange(
            final Duration duration
    ) {
        LocalDateTime now = LocalDateTime.now();
        List<Owner> owners = ownerDao.getAllByBirthDateRange(Timestamp.valueOf(now),
                Timestamp.valueOf(now.plus(duration)));

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
