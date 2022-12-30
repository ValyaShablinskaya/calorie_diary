package by.it_academy.calorie_diary.services.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
