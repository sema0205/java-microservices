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

    @PostMapping
    public CatDto create(
            @RequestBody final CatDto CatDto
    ) {
        return catService.create(CatDto);
    }

    @PostMapping("/{id}")
    public CatDto addCat(
            @PathVariable final Long id,
            @RequestParam Long friendId
    ) {
        return catService.addFriend(id, friendId);
    }

    @GetMapping
    public List<CatDto> getAll(
            @RequestParam(required = false) Color color
    ) {
        if (color != null) {
            return catService.getAllByColor(color);
        }

        return catService.getAll();
    }

}

