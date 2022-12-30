package by.it_academy.calorie_diary.services.dto.user;

import by.it_academy.calorie_diary.entity.UserRole;
import by.it_academy.calorie_diary.entity.UserStatus;
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
public class UserDTO {
    private UUID id;
    private String mail;
    private String nick;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime dateCrete;
    private LocalDateTime dateUpdate;
}
