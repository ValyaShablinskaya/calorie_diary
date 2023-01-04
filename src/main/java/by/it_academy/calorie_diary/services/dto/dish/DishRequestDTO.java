package by.it_academy.calorie_diary.services.dto.dish;

import by.it_academy.calorie_diary.services.dto.composition.CompositionRequestDTO;
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
public class DishRequestDTO {
    @NotBlank
    private String title;
    private List<CompositionRequestDTO> compositions;
}
