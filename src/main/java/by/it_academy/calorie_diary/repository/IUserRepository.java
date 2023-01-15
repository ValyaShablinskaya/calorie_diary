package by.it_academy.calorie_diary.repository;

import by.it_academy.calorie_diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByMail(String mail);
    @Query("select v.user from VerificationToken v where v.token = :token")
    Optional<User> findUserByToken(@Param("token") String token);
}
