package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.services.api.IDiaryService;
import by.it_academy.calorie_diary.services.dto.*;
import by.it_academy.calorie_diary.services.dto.diary.DiaryRequestDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/profile/{uuid_profile}/journal/food")
public class DiaryController {
    private final IDiaryService service;

    public DiaryController(IDiaryService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<Diary> createDiary(@Valid @PathVariable UUID uuid_profile, @RequestBody DiaryRequestDTO data) {
        Diary created = this.service.create(data, uuid_profile);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    protected ResponseEntity<PageDTO<Diary>> getListOfDiary(@PathVariable UUID uuid_profile, Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable, uuid_profile));
    }
}
