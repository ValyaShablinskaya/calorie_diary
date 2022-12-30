package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.Composition;
import by.it_academy.calorie_diary.entity.Dish;
import by.it_academy.calorie_diary.mappers.ICompositionMapper;
import by.it_academy.calorie_diary.mappers.IDishMapper;
import by.it_academy.calorie_diary.mappers.IProductMapper;
import by.it_academy.calorie_diary.repository.IDishRepository;
import by.it_academy.calorie_diary.repository.IProductRepository;
import by.it_academy.calorie_diary.services.api.IDishService;
import by.it_academy.calorie_diary.services.dto.dish.DishDTO;
import by.it_academy.calorie_diary.services.dto.dish.DishRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DishService implements IDishService {
    private final IDishRepository repository;
    private final IProductRepository productRepository;
    private final IDishMapper dishMapper;
    private final ICompositionMapper compositionMapper;
    private final IProductMapper productMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Dish is not found";

    public DishService(IDishRepository repository, IProductRepository productRepository, IDishMapper dishMapper,
                       ICompositionMapper compositionMapper, IProductMapper productMapper) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.dishMapper = dishMapper;
        this.compositionMapper = compositionMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public DishDTO create(DishRequestDTO item) {
        Dish dish = new Dish();
        dish.setId(UUID.randomUUID());
        dish.setDateCrete(LocalDateTime.now());
        dish.setDateUpdate(dish.getDateCrete());
        dish.setTitle(item.getTitle());
        dish.setCompositions(item.getCompositions().stream()
                .map(i ->
                        new Composition(
                                i.getMeasureOfWeight(),
                                i.getWeigh(),
                                productRepository.getById(i.getProduct().getId())))
                .collect(Collectors.toList()));
        dish = repository.save(dish);
        return dishMapper.convertToDTO(dish);
    }

    @Override
    public DishDTO read(UUID id) {
        Dish dish = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return  dishMapper.convertToDTO(dish);
    }

    @Override
    public PageDTO<Dish> get(Pageable pageable) {
        Page<Dish> content = repository.findAll(pageable);
        PageDTO<Dish> pageDTO = new PageDTO();
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
    public Dish update(DishRequestDTO item, UUID id, LocalDateTime updateData) {
        Dish read = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!read.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException("Dish has been already edited");
        }
        read.setDateUpdate(LocalDateTime.now());
        read.setTitle(item.getTitle());
        read.setCompositions(item.getCompositions().stream()
                .map(i ->
                        new Composition(
                                i.getMeasureOfWeight(),
                                i.getWeigh(),
                                productRepository.getById(i.getProduct().getId())))
                .collect(Collectors.toList()));

        return repository.save(read);
    }

    @Override
    @Transactional
    public void delete(UUID id, LocalDateTime updateData) {
        Dish dish = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!dish.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException("Dish has been already deleted");
        }
        repository.deleteById(id);
    }
}
