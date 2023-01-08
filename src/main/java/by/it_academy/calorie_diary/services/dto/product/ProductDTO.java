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
    private Integer calories;
    @NotNull
    private Double proteins;
    @NotNull
    private Double fats;
    @NotNull
    private Double carbohydrates;
    @NotNull
    private MeasureOfWeight measureOfWeight;
    @NotNull
    private Integer weight;
}
