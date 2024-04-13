package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.OwnerService;
import org.example.cat.Color;
import org.example.dto.OwnerDto;
import org.example.dto.TimeRangeRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PutMapping
    public OwnerDto update(
            @RequestBody final OwnerDto dto
    ) {
        return ownerService.update(dto);
    }

    @GetMapping("/{id}")
    public OwnerDto getById(
            @PathVariable final Long id
    ) {
        return ownerService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable final Long id
    ) {
        ownerService.delete(id);
    }

    @PostMapping
    public OwnerDto create(
            @RequestBody final OwnerDto ownerDto
    ) {
        return ownerService.create(ownerDto);
    }

    @PostMapping("/{id}")
    public OwnerDto addCat(
            @PathVariable final Long id,
            @RequestParam Long catId
    ) {
        return ownerService.addCat(id, catId);
    }

    @GetMapping
    public List<OwnerDto> getAll(
            @RequestParam(required = false) TimeRangeRequest range
    ) {
        if (range != null) {
            ownerService.getAllByBirthDateRange(range.getStart(), range.getEnd());
        }

        return ownerService.getAll();
    }

}
