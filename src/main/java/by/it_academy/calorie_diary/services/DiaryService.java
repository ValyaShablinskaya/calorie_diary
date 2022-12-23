package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.mappers.IDiaryMapper;
import by.it_academy.calorie_diary.mappers.IDishMapper;
import by.it_academy.calorie_diary.mappers.IProductMapper;
import by.it_academy.calorie_diary.repository.IDiaryRepository;
import by.it_academy.calorie_diary.repository.IDishRepository;
import by.it_academy.calorie_diary.repository.IProductRepository;
import by.it_academy.calorie_diary.services.api.IDiaryService;
import by.it_academy.calorie_diary.services.dto.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.DiaryRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DiaryService implements IDiaryService {
    private final IDiaryRepository repository;
    private final IDishRepository dishRepository;
    private final IProductRepository productRepository;
    private final IDiaryMapper diaryMapper;
    private final IProductMapper productMapper;
    private final IDishMapper dishMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Diary is not found";

    public DiaryService(IDiaryRepository repository, IDishRepository dishRepository,
                        IProductRepository productRepository, IDiaryMapper diaryMapper,
                        IProductMapper productMapper, IDishMapper dishMapper) {
        this.repository = repository;
        this.dishRepository = dishRepository;
        this.productRepository = productRepository;
        this.diaryMapper = diaryMapper;
        this.productMapper = productMapper;
        this.dishMapper = dishMapper;
    }

    @Override
    @Transactional
    public DiaryDTO create(DiaryRequestDTO item) {
        Diary diary = populateDiary(item);
        diary = repository.save(diary);
        return diaryMapper.convertToDTO(diary);
    }


    @Override
    public DiaryDTO read(UUID id) {
        Diary diary = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return  diaryMapper.convertToDTO(diary);
    }

    @Override
    public PageDTO<Diary> get(Pageable pageable) {
        Page<Diary> content = repository.findAll(pageable);
        PageDTO<Diary> pageDTO = new PageDTO();
        pageDTO.setNumber(content.getNumber());
        pageDTO.setSize(content.getSize());
        pageDTO.setTotalPages(content.getTotalPages());
        pageDTO.setTotalElements(content.getTotalElements());
        pageDTO.setFirst(content.isFirst());
        pageDTO.setNumberOfElements(content.getNumberOfElements());
        pageDTO.setLast(content.isLast());
        pageDTO.setContent(content.getContent());
        return pageDTO;
    }

    @Override
    @Transactional
    public DiaryDTO update(DiaryRequestDTO item, UUID id, LocalDateTime updateData) {
        Diary read = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!read.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException("Diary has been already edited");
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

        read = repository.save(read);
        return diaryMapper.convertToDTO(read);
    }

    @Override
    @Transactional
    public void delete(UUID id, LocalDateTime updateData) {
        Diary diary = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!diary.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException("Diary has been already deleted");
        }
        repository.deleteById(id);
    }

    private Diary populateDiary(DiaryRequestDTO item) {
        Diary diary = new Diary();
        diary.setId(UUID.randomUUID());
        diary.setDateCrete(LocalDateTime.now());
        diary.setDateUpdate(diary.getDateCrete());
        diary.setMeasureOfWeight(item.getMeasureOfWeight());
        diary.setWeight(item.getWeight());

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
                new EntityNotFoundException("Dish is not found"));
    }

    private Product findProductIsExist(DiaryRequestDTO item) {
        return productRepository.findById(item.getProduct().getId()).orElseThrow(() ->
                new EntityNotFoundException("Product is not found"));
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
                ZoneId.of("UTC")
        );
    }
}
