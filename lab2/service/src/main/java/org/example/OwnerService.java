package org.example;

import org.example.dto.OwnerDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
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
            Timestamp start,
            Timestamp end
    );

    List<OwnerDto> getAll();

}
