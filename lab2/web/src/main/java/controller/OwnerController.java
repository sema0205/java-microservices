package controller;

import lombok.RequiredArgsConstructor;
import org.example.OwnerService;
import org.example.dto.OwnerDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PutMapping
    public OwnerDto update(
            @RequestBody @Argument final OwnerDto dto
    ) {
        return ownerService.update(dto);
    }

    @GetMapping("/{id}")
    public OwnerDto getById(
            @PathVariable @Argument final Long id
    ) {
        return ownerService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable @Argument final Long id
    ) {
        ownerService.delete(id);
    }

    @PostMapping("/create")
    public OwnerDto create(
            @RequestBody final OwnerDto ownerDto
    ) {
        return ownerService.create(ownerDto);
    }

    @PostMapping("/{id}/cat/add")
    public OwnerDto addCat(
            @PathVariable @Argument final Long id,
            @RequestBody @Argument final Long catId
    ) {
        return ownerService.addCat(id, catId);
    }

    @GetMapping("/all")
    public List<OwnerDto> getAll() {
        return ownerService.getAll();
    }

    @GetMapping("/birthdate")
    public List<OwnerDto> getAllByBirthDateRange(
            @RequestBody @Argument final Duration duration
    ) {
        return ownerService.getAllByBirthDateRange(duration);
    }

}
