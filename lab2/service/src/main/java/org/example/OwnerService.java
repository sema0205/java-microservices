package org.example;

import org.example.dto.OwnerDto;

import java.time.Duration;
import java.util.List;

public interface OwnerService {

    OwnerDto create(
            OwnerDto owner
    );

    OwnerDto update(
            OwnerDto owner
    );

    void delete(
            Long id
    );

    OwnerDto addCat(
            Long ownerId,
            Long catId
    );

    OwnerDto getById(
            Long id
    );

    List<OwnerDto> getAllByBirthDateRange(
            Duration duration
    );

    List<OwnerDto> getAll();

}
