package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.services.dto.profile.ProfileDTO;
import by.it_academy.calorie_diary.services.dto.profile.ProfileRequestDTO;

import java.util.UUID;

public interface IProfileService {
    ProfileDTO create(ProfileRequestDTO item);
    ProfileDTO read(UUID id);
}
