package by.it_academy.calorie_diary.mappers;

import by.it_academy.calorie_diary.entity.Diary;
import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.services.dto.diary.DiaryDTO;
import by.it_academy.calorie_diary.services.dto.user.UserDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface IDiaryMapper {
    DiaryDTO convertToDTO(Diary diary);

    Diary convertToEntity(DiaryDTO diaryDTO);

    List<DiaryDTO> convertToListDTO(List<Diary> diaries);
}
