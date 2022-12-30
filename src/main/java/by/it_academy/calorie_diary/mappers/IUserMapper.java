package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.services.dto.user.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDTO convertToDTO(User user);
    List<UserDTO> convertToListDTO(List<User> users);
}
