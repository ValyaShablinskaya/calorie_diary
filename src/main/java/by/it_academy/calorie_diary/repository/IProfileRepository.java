package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findProfileByUser(User user);
}
