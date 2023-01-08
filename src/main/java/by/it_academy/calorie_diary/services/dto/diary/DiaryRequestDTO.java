package by.it_academy.calorie_diary.services.dto.diary;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestForDiaryDTO;
import by.it_academy.calorie_diary.services.dto.product.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryRequestDTO {
    @NotNull
    private MeasureOfWeight measureOfWeight;
    @NotNull
    private Integer weight;
    private Integer date;
    private DishRequestForDiaryDTO dish;
    private ProductRequestDTO product;
}
