package by.it_academy.calorie_diary.services.dto.composition;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.product.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompositionRequestDTO {
    @NotBlank
    private MeasureOfWeight measureOfWeight;
    @NotBlank
    private int weigh;
    @NotBlank
    private ProductRequestDTO product;
}
