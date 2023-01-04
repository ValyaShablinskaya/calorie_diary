package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.user.UserCreateDTO;
import by.it_academy.calorie_diary.services.dto.user.UserDTO;
import by.it_academy.calorie_diary.services.dto.user.UserRegistrationDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    UserDTO createUser(UserCreateDTO item);
    UserDTO registrationUser(UserRegistrationDTO item);
    UserDTO read(UUID id);
    PageDTO<UserDTO> get(Pageable pageable);
    UserDTO update(UserCreateDTO item, UUID id, LocalDateTime updateData);
    UserDTO getInfoAboutUser();
    String findCurrentUser();
    User getCurrentUserByMail(String mail);
}
