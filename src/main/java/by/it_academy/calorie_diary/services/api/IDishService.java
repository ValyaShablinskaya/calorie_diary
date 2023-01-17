package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDishService {
    Dish create(DishRequestDTO item);
    Dish read(UUID id);
    PageDTO<Dish> get(Pageable pageable);
    Dish update(DishRequestDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}
