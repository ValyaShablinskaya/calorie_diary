package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.services.dto.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.DiaryRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDiaryService {
    DiaryDTO create(DiaryRequestDTO item);
    DiaryDTO read(UUID id);
    PageDTO<Diary> get(Pageable pageable);
    DiaryDTO update(DiaryRequestDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}
