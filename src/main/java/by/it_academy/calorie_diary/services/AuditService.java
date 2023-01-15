package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.*;
import by.it_academy.calorie_diary.mappers.IAuditMapper;
import by.it_academy.calorie_diary.repository.IAuditRepository;
import by.it_academy.calorie_diary.services.api.IAuditService;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.audit.AuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AuditService implements IAuditService {
    private final IAuditRepository auditRepository;
    private final IAuditMapper auditMapper;
    private static final String AUDIT_NOT_FOUND_EXCEPTION = "Audit is not found";

    public AuditService(IAuditRepository auditRepository, IAuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.auditMapper = auditMapper;
    }

    @Override
    @Transactional
    public Audit create(Audit item) {
        item.setId(UUID.randomUUID());
        return auditRepository.save(item);
    }

    @Override
    public AuditDTO read(UUID id) {
        Audit audit = auditRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AUDIT_NOT_FOUND_EXCEPTION));
        return auditMapper.convertToDTO(audit);
    }

    @Override
    public PageDTO<AuditDTO> get(Pageable pageable) {
        Page<Audit> content = auditRepository.findAll(pageable);

        PageDTO<AuditDTO> pageDTO = new PageDTO();
        pageDTO.setNumber(content.getNumber());
        pageDTO.setSize(content.getSize());
        pageDTO.setTotalPages(content.getTotalPages());
        pageDTO.setTotalElements(content.getTotalElements());
        pageDTO.setFirst(content.isFirst());
        pageDTO.setNumberOfElements(content.getNumberOfElements());
        pageDTO.setLast(content.isLast());
        pageDTO.setContent(auditMapper.convertToListDTO(content.getContent()));
        return pageDTO;
    }
}
