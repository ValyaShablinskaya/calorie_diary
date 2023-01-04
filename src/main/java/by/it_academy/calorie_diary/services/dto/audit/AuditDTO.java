package by.it_academy.calorie_diary.services.dto.audit;

import by.it_academy.calorie_diary.entity.EssenceType;
import by.it_academy.calorie_diary.services.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {
    private UUID id;
    private UserDTO user;
    private String text;
    private EssenceType type;
}
