package controller;

import lombok.RequiredArgsConstructor;
import org.example.CatService;
import org.example.cat.Color;
import org.example.dto.CatDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PutMapping
    public CatDto update(
            @RequestBody @Argument final CatDto dto
    ) {
        return catService.update(dto);
    }

    @GetMapping("/{id}")
    public CatDto getById(
            @PathVariable @Argument final Long id
    ) {
        return catService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable @Argument final Long id
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
            @PathVariable @Argument final Long id,
            @RequestBody @Argument final Long friendId
    ) {
        return catService.addFriend(id, friendId);
    }

    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAll();
    }

    @GetMapping("/birthdate")
    public List<CatDto> getAllByBirthDateRange(
            @RequestBody @Argument final Color color
    ) {
        return catService.getAllByColor(color);
    }

}

