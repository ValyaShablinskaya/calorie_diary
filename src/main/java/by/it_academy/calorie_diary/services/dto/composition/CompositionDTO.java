package by.it_academy.calorie_diary.services.dto.composition;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompositionDTO {
    private MeasureOfWeight measureOfWeight;
    private int weigh;
    private ProductDTO product;
}