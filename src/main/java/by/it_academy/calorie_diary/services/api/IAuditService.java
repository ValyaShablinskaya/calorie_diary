package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Audit;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.audit.AuditDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAuditService {
    Audit create(Audit item);
    List<AuditDTO> readByUserId(UUID userId);
    PageDTO<AuditDTO> get(Pageable pageable);
}
