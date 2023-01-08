package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.mappers.IProfileMapper;
import by.it_academy.calorie_diary.repository.IProfileRepository;
import by.it_academy.calorie_diary.services.api.IProfileService;
import by.it_academy.calorie_diary.services.dto.profile.ProfileDTO;
import by.it_academy.calorie_diary.services.dto.profile.ProfileRequestDTO;
import by.it_academy.calorie_diary.services.exception.AccessIsDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProfileService implements IProfileService {
    private final IProfileRepository repository;
    private final IProfileMapper mapper;
    private final UserService userService;
    private static final String PROFILE_ALREADY_EXIST_EXCEPTION = "Profile already exist";
    private static final String PROFILE_NOT_FOUND_EXCEPTION = "Profile is not found";
    private static final String ACCESS_USER_DENIED_EXCEPTION = "Current user can not read profile";

    public ProfileService(IProfileRepository repository, IProfileMapper mapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ProfileDTO create(ProfileRequestDTO item) {
        if (isProfileExist()) {
            throw new EntityExistsException(PROFILE_ALREADY_EXIST_EXCEPTION);
        }

        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        profile.setDateCrete(LocalDateTime.now());
        profile.setDateUpdate(profile.getDateCrete());
        profile.setUser(userService.findCurrentUser());
        profile.setHeight(item.getHeight());
        profile.setWeight(item.getWeight());
        profile.setDateBirthday(item.getDateBirthday());
        profile.setTarget(item.getTarget());
        profile.setActivityType(item.getActivityType());
        profile.setSex(item.getSex());

        profile = repository.save(profile);
        return mapper.convertToDTO(profile);

    }

    @Override
    public ProfileDTO read(UUID id) {
        UUID currentUserId = userService.findCurrentUser().getId();
        Profile profile = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(PROFILE_NOT_FOUND_EXCEPTION));
        if (!profile.getUser().getId().equals(currentUserId)) {
            throw new AccessIsDeniedException(ACCESS_USER_DENIED_EXCEPTION);
        }
        return  mapper.convertToDTO(profile);
    }

    private Boolean isProfileExist() {
        return repository.findProfileByUser(userService.findCurrentUser()).isPresent();
    }
}