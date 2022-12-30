package by.it_academy.calorie_diary.services.dto.dish;


import by.it_academy.calorie_diary.services.dto.composition.CompositionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    @NotBlank
    private String title;
    @NotBlank
    private List<CompositionDTO> compositions;
}
