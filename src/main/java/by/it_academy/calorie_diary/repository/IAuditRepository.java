package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface IAuditRepository extends JpaRepository<Audit, UUID> {
}
