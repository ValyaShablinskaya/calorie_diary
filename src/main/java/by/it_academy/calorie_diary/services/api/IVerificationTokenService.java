package by.it_academy.calorie_diary.services.api;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.entity.VerificationToken;

public interface IVerificationTokenService {
    VerificationToken saveToken(User user);
    VerificationToken findByToken(String token);
    boolean validateVerificationToken(String token);
    void removeToken(String token);
}
