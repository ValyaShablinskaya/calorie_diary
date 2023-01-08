package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.mappers.IProductMapper;
import by.it_academy.calorie_diary.repository.IProductRepository;
import by.it_academy.calorie_diary.services.api.IProductService;
import by.it_academy.calorie_diary.services.api.IUserService;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.product.ProductDTO;
import by.it_academy.calorie_diary.services.exception.AccessIsDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final IProductMapper productMapper;
    private final IUserService userService;
    private static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product is not found";
    private static final String PRODUCT_ALREADY_EDITED_EXCEPTION = "Product has been already edited";
    private static final String PRODUCT_ALREADY_DELETED_EXCEPTION = "Product has been already deleted";
    private static final String ACCESS_USER_DENIED_EXCEPTION = "Current user can not update product";

    public ProductService(IProductRepository repository, IProductMapper productMapper, IUserService userService) {
        this.repository = repository;
        this.productMapper = productMapper;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ProductDTO create(ProductDTO item) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setDateCrete(LocalDateTime.now());
        product.setDateUpdate(product.getDateCrete());
        product.setTitle(item.getTitle());
        product.setFats(item.getFats());
        product.setCalories(item.getCalories());
        product.setProteins(item.getProteins());
        product.setCarbohydrates(item.getCarbohydrates());
        product.setMeasureOfWeight(item.getMeasureOfWeight());
        product.setWeight(item.getWeight());
        product.setUser(userService.findCurrentUser());

        product = repository.save(product);
        return productMapper.convertToDTO(product);
    }

    @Override
    public ProductDTO read(UUID id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
        return  productMapper.convertToDTO(product);
    }

    @Override
    public PageDTO<Product> get(Pageable pageable) {
        Page<Product> content = repository.findAll(pageable);

        PageDTO<Product> pageDTO = new PageDTO();
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
    public ProductDTO update(ProductDTO item, UUID id, LocalDateTime updateData) {
        Product read = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
        if (!read.getUser().equals(userService.findCurrentUser())) {
            throw new AccessIsDeniedException(ACCESS_USER_DENIED_EXCEPTION);
        }
        if (!read.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException(PRODUCT_ALREADY_EDITED_EXCEPTION);
        }

        read.setDateUpdate(LocalDateTime.now());
        read.setTitle(item.getTitle());
        read.setCalories(item.getCalories());
        read.setProteins(item.getProteins());
        read.setFats(item.getFats());
        read.setCarbohydrates(item.getCarbohydrates());
        read.setMeasureOfWeight(item.getMeasureOfWeight());
        read.setWeight(item.getWeight());
        read.setUser(userService.findCurrentUser());

        read = repository.save(read);
        return productMapper.convertToDTO(read);
    }

    @Override
    @Transactional
    public void delete(UUID id, LocalDateTime updateData) {
        Product product = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
        if (!product.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException(PRODUCT_ALREADY_DELETED_EXCEPTION);
        }
        repository.deleteById(id);
    }
}
