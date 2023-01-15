package by.it_academy.calorie_diary.services.dto.user;

import by.it_academy.calorie_diary.entity.UserRole;
import by.it_academy.calorie_diary.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @NotBlank
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
            message = "Email invalid")
    private String mail;
    @NotBlank
    private String nick;
    @NotNull
    private UserRole role;
    @NotNull
    private UserStatus status;
    @NotBlank
    private String password;
}
