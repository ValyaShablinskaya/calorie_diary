package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Composition;
import by.it_academy.calorie_diary.services.dto.composition.CompositionDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        uses = IProductMapper.class)
public interface ICompositionMapper {
    CompositionDTO convertToDTO(Composition composition);

    Composition convertToEntity(CompositionDTO compositionDTO);

    List<CompositionDTO> convertToList(List<Composition> compositions);

    List<Composition> convertToListFromDto(List<CompositionDTO> compositionDTOList);
}
