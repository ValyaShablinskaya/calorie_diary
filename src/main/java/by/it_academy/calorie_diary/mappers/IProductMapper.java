package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.services.dto.ProductDTO;
import by.it_academy.calorie_diary.services.dto.ProductRequestDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface IProductMapper {
    ProductDTO convertToDTO(Product product);

    Product convertToEntity(ProductDTO productDTO);
    Product convertToEntityFromProductRequestDTO(ProductRequestDTO productRequestDTO);

    List<ProductDTO> convertToList(List<Product> products);
}
