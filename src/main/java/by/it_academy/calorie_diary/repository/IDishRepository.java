package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDishRepository extends JpaRepository<Dish, UUID> {
}
