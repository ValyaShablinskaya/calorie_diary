package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByMail(String mail);
}
