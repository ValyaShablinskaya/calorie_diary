package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Audit;
import by.it_academy.calorie_diary.services.dto.audit.AuditDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface IAuditMapper {
    AuditDTO convertToDTO(Audit audit);
    List<AuditDTO> convertToListDTO(List<Audit> audits);
}
