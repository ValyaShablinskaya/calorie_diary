package by.it_academy.calorie_diary.services.dto;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String title;
    private int calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private MeasureOfWeight measureOfWeight;
    private int weight;
}
