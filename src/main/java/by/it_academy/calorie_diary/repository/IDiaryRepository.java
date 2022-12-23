package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDiaryRepository extends JpaRepository<Diary, UUID> {
}
