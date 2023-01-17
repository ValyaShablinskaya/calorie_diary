package by.it_academy.calorie_diary.services.dto.profile;

import by.it_academy.calorie_diary.entity.ActivityType;
import by.it_academy.calorie_diary.entity.Sex;
import by.it_academy.calorie_diary.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDTO {
    @NotNull
    private Integer height;
    @NotNull
    private Double weight;
    @NotNull
    private LocalDate dateBirthday;
    @NotNull
    private Double target;
    @NotNull
    private ActivityType activityType;
    @NotNull
    private Sex sex;
}
