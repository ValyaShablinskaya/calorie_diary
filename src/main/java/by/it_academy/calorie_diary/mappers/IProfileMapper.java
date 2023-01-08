package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.services.dto.profile.ProfileDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProfileMapper {
    ProfileDTO convertToDTO(Profile profile);
}
