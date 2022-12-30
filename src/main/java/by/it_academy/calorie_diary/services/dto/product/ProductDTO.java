package by.it_academy.calorie_diary.services.dto.product;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank
    private String title;
    private int calories;
    @NotBlank
    private double proteins;
    @NotBlank
    private double fats;
    @NotBlank
    private double carbohydrates;
    @NotBlank
    private MeasureOfWeight measureOfWeight;
    @NotBlank
    private int weight;
}
