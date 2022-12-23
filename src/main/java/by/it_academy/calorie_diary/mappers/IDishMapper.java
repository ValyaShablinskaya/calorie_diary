package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.services.dto.DishDTO;
import by.it_academy.calorie_diary.services.dto.DishRequestDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
    uses = {ICompositionMapper.class, IProductMapper.class})
public interface IDishMapper {
    DishDTO convertToDTO(Dish dish);

    Dish convertToEntity(DishDTO dishDTO);

    Dish convertToEntityFromDishRequestDTO(DishRequestDTO dishRequestDTO);

    List<DishDTO> convertToList(List<Dish> dishes);
}
