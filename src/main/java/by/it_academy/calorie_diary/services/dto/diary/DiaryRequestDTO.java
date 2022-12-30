package by.it_academy.calorie_diary.services.dto.diary;

import by.it_academy.calorie_diary.entity.MeasureOfWeight;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestForDiaryDTO;
import by.it_academy.calorie_diary.services.dto.product.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryRequestDTO {
    private MeasureOfWeight measureOfWeight;
    private int weight;
    private int date;
    private DishRequestForDiaryDTO dish;
    private ProductRequestDTO product;
}
