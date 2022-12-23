package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.services.api.IDishService;
import by.it_academy.calorie_diary.services.dto.DishDTO;
import by.it_academy.calorie_diary.services.dto.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
public class DishController {
    private final IDishService service;

    public DishController(IDishService service) {
        this.service = service;
    }
    @PostMapping
    protected ResponseEntity<DishDTO> createDish(@RequestBody DishRequestDTO data) {
        DishDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    protected ResponseEntity<PageDTO<Dish>> getList(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }
}
