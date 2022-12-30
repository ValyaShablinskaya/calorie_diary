package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.repository.IUserRepository;
import by.it_academy.calorie_diary.security.jwt.JwtTokenProvider;
import by.it_academy.calorie_diary.services.api.IAuthenticationService;
import by.it_academy.calorie_diary.services.dto.user.JwtResponseDTO;
import by.it_academy.calorie_diary.services.dto.user.UserLoginDTO;
import by.it_academy.calorie_diary.services.exception.UserAuthenticationProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final AuthenticationManager authenticationManager;
    private static final String AUTHENTICATION_EXCEPTION = "Invalid username/password supplied";
    private static final String USER_NOT_FOUND_EXCEPTION = "Specified user by email is not found";

    @Override
    public JwtResponseDTO authenticate(UserLoginDTO userLoginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getMail(),
                    userLoginDTO.getPassword()));
            User user = getUserByMailIfExist(userLoginDTO.getMail());
            String accessToken = jwtTokenProvider.generateAccessToken(user);
            String refreshToken = jwtTokenProvider.generateRefreshToken(user);
            refreshStorage.put(user.getMail(), refreshToken);
            return new JwtResponseDTO(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw new UserAuthenticationProcessingException(AUTHENTICATION_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public JwtResponseDTO getAccessToken(String refreshToken) {
        if (jwtTokenProvider.validateRefreshToken(refreshToken)) {
            String email = jwtTokenProvider.getLoginFromRefreshToken(refreshToken);
            String savedRefreshToken = refreshStorage.get(email);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                User user = getUserByMailIfExist(email);
                String accessToken = jwtTokenProvider.generateAccessToken(user);
                return new JwtResponseDTO(accessToken, null);
            }
        }
        return new JwtResponseDTO(null, null);
    }

    @Override
    public JwtResponseDTO getRefreshToken(String refreshToken) {
        if (jwtTokenProvider.validateRefreshToken(refreshToken)) {
            String email = jwtTokenProvider.getLoginFromRefreshToken(refreshToken);
            String savedRefreshToken = refreshStorage.get(email);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                User user = getUserByMailIfExist(email);
                String accessToken = jwtTokenProvider.generateAccessToken(user);
                String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);
                refreshStorage.put(user.getMail(), newRefreshToken);
                return new JwtResponseDTO(accessToken, newRefreshToken);
            }
        }
        throw new UserAuthenticationProcessingException(AUTHENTICATION_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler contextLogoutHandler = new SecurityContextLogoutHandler();
        contextLogoutHandler.logout(request, response, null);
    }

    private User getUserByMailIfExist(String mail) {
        return repository.findByMail(mail).orElseThrow(() ->
                new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION));
    }
}
