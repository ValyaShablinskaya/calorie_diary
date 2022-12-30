package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.services.api.IDiaryService;
import by.it_academy.calorie_diary.services.dto.*;
import by.it_academy.calorie_diary.services.dto.diary.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.diary.DiaryRequestDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journal/food")
public class DiaryController {
    private final IDiaryService service;

    public DiaryController(IDiaryService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<DiaryDTO> createDiary(@RequestBody DiaryRequestDTO data) {
        DiaryDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    protected ResponseEntity<PageDTO<Diary>> getListOfDiary(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }
}
