package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.CatService;
import org.example.cat.Color;
import org.example.dto.CatDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PutMapping
    public CatDto update(
            @RequestBody final CatDto dto
    ) {
        return catService.update(dto);
    }

    @GetMapping("/{id}")
    public CatDto getById(
            @PathVariable final Long id
    ) {
        return catService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable final Long id
    ) {
        catService.delete(id);
    }

    @PostMapping("/create")
    public CatDto create(
            @RequestBody final CatDto CatDto
    ) {
        return catService.create(CatDto);
    }

    @PostMapping("/{id}/friend/add")
    public CatDto addCat(
            @PathVariable final Long id,
            @RequestBody final Long friendId
    ) {
        return catService.addFriend(id, friendId);
    }

    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAll();
    }

    @GetMapping("/color")
    public List<CatDto> getAllByBirthDateRange(
            @RequestBody final Color color
    ) {
        return catService.getAllByColor(color);
    }

}

