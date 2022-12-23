package by.it_academy.calorie_diary.services.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private String title;
    private List<CompositionDTO> compositions;
}
