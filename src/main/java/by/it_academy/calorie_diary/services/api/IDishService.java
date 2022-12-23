package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.services.dto.DishDTO;
import by.it_academy.calorie_diary.services.dto.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDishService {
    DishDTO create(DishRequestDTO item);
    DishDTO read(UUID id);
    PageDTO<Dish> get(Pageable pageable);
    DishDTO update(DishRequestDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}