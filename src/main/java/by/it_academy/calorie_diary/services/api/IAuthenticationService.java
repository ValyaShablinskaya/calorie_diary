package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.services.dto.user.JwtResponseDTO;
import by.it_academy.calorie_diary.services.dto.user.UserLoginDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
    JwtResponseDTO authenticate(UserLoginDTO userLoginDTO);

    JwtResponseDTO recreateToken(String refreshToken);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
