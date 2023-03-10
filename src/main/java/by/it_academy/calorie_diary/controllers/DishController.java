package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.services.api.IDishService;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@Valid
@RequestMapping("/api/v1/recipe")
public class DishController {
    private final IDishService service;

    public DishController(IDishService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<Dish> createDish(@Valid @RequestBody DishRequestDTO data) {
        Dish created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    protected ResponseEntity<PageDTO<Dish>> getListOfDish(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<Dish> updateDish(@Valid @PathVariable UUID id,
                                            @PathVariable("update_date") long updateTime,
                                            @RequestBody DishRequestDTO data) {

        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateTime),
                TimeZone.getDefault().toZoneId());

        return ResponseEntity.ok(this.service.update(data, id, updateDate));
    }
}
