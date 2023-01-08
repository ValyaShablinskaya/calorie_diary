package by.it_academy.calorie_diary.services.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForProfileDTO {
    private UUID id;
    private LocalDateTime dateCrete;
    private LocalDateTime dateUpdate;
}
