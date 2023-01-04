package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IAuditRepository extends JpaRepository<Audit, UUID> {
    @Query("select a from Audit a where a.user.id = :id")
    List<Audit> findByUserId(@Param("id") UUID userId);
}
