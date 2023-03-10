package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.mappers.IDiaryMapper;
import by.it_academy.calorie_diary.repository.IDiaryRepository;
import by.it_academy.calorie_diary.repository.IDishRepository;
import by.it_academy.calorie_diary.repository.IProductRepository;
import by.it_academy.calorie_diary.repository.IProfileRepository;
import by.it_academy.calorie_diary.services.api.IDiaryService;
import by.it_academy.calorie_diary.services.api.IUserService;
import by.it_academy.calorie_diary.services.dto.diary.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.diary.DiaryRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.exception.AccessIsDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DiaryService implements IDiaryService {
    private final IDiaryRepository repository;
    private final IDishRepository dishRepository;
    private final IProductRepository productRepository;
    private final IDiaryMapper diaryMapper;
    private final IProfileRepository profileRepository;
    private final IUserService userService;
    private static final String DIARY_NOT_FOUND_EXCEPTION = "Diary is not found";
    private static final String DISH_NOT_FOUND_EXCEPTION = "Dish is not found";
    private static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product is not found";
    private static final String DIARY_ALREADY_EDITED_EXCEPTION = "Diary has been already edited";
    private static final String DIARY_ALREADY_DELETED_EXCEPTION = "Diary has been already deleted";
    private static final String PROFILE_NOT_FOUND_EXCEPTION = "Profile is not found";
    private static final String ACCESS_USER_DENIED_EXCEPTION = "Current user can not create/get diary";

    public DiaryService(IDiaryRepository repository, IDishRepository dishRepository, IProductRepository productRepository,
                        IDiaryMapper diaryMapper, IProfileRepository profileRepository, IUserService userService) {
        this.repository = repository;
        this.dishRepository = dishRepository;
        this.productRepository = productRepository;
        this.diaryMapper = diaryMapper;
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Diary create(DiaryRequestDTO item, UUID uuid_profile) {
        if (!findProfileById(uuid_profile).getUser().equals(userService.findCurrentUser())) {
            throw new AccessIsDeniedException(ACCESS_USER_DENIED_EXCEPTION);
        }
        Diary diary = populateDiary(item, uuid_profile);

        return repository.save(diary);
    }

    @Override
    public Diary read(UUID id) {
        return  repository.findById(id).orElseThrow(() -> new EntityNotFoundException(DIARY_NOT_FOUND_EXCEPTION));
    }

    @Override
    public PageDTO<Diary> get(Pageable pageable, UUID uuid_profile) {
        if (!findProfileById(uuid_profile).getUser().equals(userService.findCurrentUser())) {
            throw new AccessIsDeniedException(ACCESS_USER_DENIED_EXCEPTION);
        }
        Page<Diary> content = repository.findAll(pageable);
        PageDTO<Diary> page = new PageDTO();
        page.setNumber(content.getNumber());
        page.setSize(content.getSize());
        page.setTotalPages(content.getTotalPages());
        page.setTotalElements(content.getTotalElements());
        page.setFirst(content.isFirst());
        page.setNumberOfElements(content.getNumberOfElements());
        page.setLast(content.isLast());
        page.setContent(content.getContent());
        return page;
    }

    @Override
    @Transactional
    public Diary update(DiaryRequestDTO item, UUID id, LocalDateTime updateData) {
        Diary read = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(DIARY_NOT_FOUND_EXCEPTION));
        if (!read.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException(DIARY_ALREADY_EDITED_EXCEPTION);
        }
        read.setDateUpdate(LocalDateTime.now());
        read.setDate(convertToDateTime(item));
        read.setMeasureOfWeight(item.getMeasureOfWeight());
        read.setWeight(item.getWeight());

        if (isDishExist(item)) {
            read.setDish(findDishIsExist(item));
        }

        if (isProductExist(item)) {
            read.setProduct(findProductIsExist(item));
        }

        return repository.save(read);
    }

    @Override
    @Transactional
    public void delete(UUID id, LocalDateTime updateData) {
        Diary diary = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(DIARY_NOT_FOUND_EXCEPTION));
        if (!diary.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException(DIARY_ALREADY_DELETED_EXCEPTION);
        }
        repository.deleteById(id);
    }

    private Diary populateDiary(DiaryRequestDTO item, UUID uuid_profile) {
        Diary diary = new Diary();
        diary.setId(UUID.randomUUID());
        diary.setDateCrete(LocalDateTime.now());
        diary.setDateUpdate(diary.getDateCrete());
        diary.setMeasureOfWeight(item.getMeasureOfWeight());
        diary.setWeight(item.getWeight());
        diary.setProfile(findProfileById(uuid_profile));

        if (isDishExist(item)) {
            diary.setDish(findDishIsExist(item));
        }

        if (isProductExist(item)) {
            diary.setProduct(findProductIsExist(item));
        }
        diary.setDate(convertToDateTime(item));
        return diary;
    }

    private Dish findDishIsExist(DiaryRequestDTO item) {
        return dishRepository.findById(item.getDish().getId()).orElseThrow(() ->
                new EntityNotFoundException(DISH_NOT_FOUND_EXCEPTION));
    }

    private Product findProductIsExist(DiaryRequestDTO item) {
        return productRepository.findById(item.getProduct().getId()).orElseThrow(() ->
                new EntityNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
    }

    private boolean isDishExist(DiaryRequestDTO item) {
        return Objects.nonNull(item.getDish());
    }

    private boolean isProductExist(DiaryRequestDTO item) {
        return Objects.nonNull(item.getProduct());
    }

    private LocalDateTime convertToDateTime(DiaryRequestDTO item) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(item.getDate()),
                TimeZone.getDefault().toZoneId());
    }

    private Profile findProfileById(UUID uuid_profile) {
        return profileRepository.findById(uuid_profile).orElseThrow(() -> new EntityNotFoundException(PROFILE_NOT_FOUND_EXCEPTION));
    }
}
