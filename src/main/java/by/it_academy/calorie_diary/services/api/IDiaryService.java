package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.services.dto.diary.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.diary.DiaryRequestDTO;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDiaryService {
    DiaryDTO create(DiaryRequestDTO item, UUID uuid_profile);
    DiaryDTO read(UUID id);
    PageDTO<DiaryDTO> get(Pageable pageable, UUID uuid_profile);
    DiaryDTO update(DiaryRequestDTO item, UUID id, LocalDateTime updateData);
    void delete(UUID id, LocalDateTime updateData);
}
