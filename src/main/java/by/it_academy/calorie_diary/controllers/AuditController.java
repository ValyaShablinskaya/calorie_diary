package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.services.api.IAuditService;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.audit.AuditDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

    private final IAuditService service;

    public AuditController(IAuditService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<List<AuditDTO>> getAuditByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.readByUserId(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<PageDTO<AuditDTO>> getListOfProduct(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }
}
