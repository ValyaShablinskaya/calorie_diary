package by.it_academy.calorie_diary.services.dto;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
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
    private int weight;
    private LocalDateTime date;
    private DishDTO dish;
    private ProductDTO product;
}
