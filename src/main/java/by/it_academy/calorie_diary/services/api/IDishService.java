package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.services.dto.dish.DishDTO;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDishService {
    DishDTO create(DishRequestDTO item);
    DishDTO read(UUID id);
    PageDTO<DishDTO> get(Pageable pageable);
    DishDTO update(DishRequestDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}
