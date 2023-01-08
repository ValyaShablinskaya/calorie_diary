package by.it_academy.calorie_diary.services.dto.composition;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.product.ProductDTO;
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
public class CompositionDTO {
    @NotNull
    private MeasureOfWeight measureOfWeight;
    @NotNull
    private Integer weigh;
    @NotNull
    private ProductDTO product;
}
