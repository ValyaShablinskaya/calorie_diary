package by.it_academy.calorie_diary.services.dto.product;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank
    private String title;
    @NotNull
    private int calories;
    @NotNull
    private double proteins;
    @NotNull
    private double fats;
    @NotNull
    private double carbohydrates;
    @NotBlank
    private MeasureOfWeight measureOfWeight;
    @NotNull
    private int weight;
}
