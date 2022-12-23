package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {
    ProductDTO create(ProductDTO item);
    ProductDTO read(UUID id);
    PageDTO<Product> get(Pageable pageable);
    ProductDTO update(ProductDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}
