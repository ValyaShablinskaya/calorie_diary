package by.it_academy.calorie_diary.services.api;

public interface IEmailService {
    void sendRegistrationConfirmationEmail(String recipient, String token);
}
