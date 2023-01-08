package by.it_academy.calorie_diary.services.dto.profile;

import by.it_academy.calorie_diary.entity.ActivityType;
import by.it_academy.calorie_diary.entity.Sex;
import by.it_academy.calorie_diary.services.dto.user.UserForProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private UUID id;
    private Integer height;
    private Double weight;
    private LocalDate dateBirthday;
    private Double target;
    private ActivityType activityType;
    private Sex sex;
    private UserForProfileDTO user;
    private LocalDateTime dateCrete;
    private LocalDateTime dateUpdate;
}
