package by.it_academy.calorie_diary.services.dto.diary;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.dish.DishDTO;
import by.it_academy.calorie_diary.services.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {
    private MeasureOfWeight measureOfWeight;
    private Integer weight;
    private LocalDateTime date;
    private DishDTO dish;
    private ProductDTO product;
}
