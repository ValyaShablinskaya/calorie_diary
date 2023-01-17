package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.services.dto.profile.ProfileRequestDTO;

import java.util.UUID;

public interface IProfileService {
    Profile create(ProfileRequestDTO item);
    Profile read(UUID id);
}
