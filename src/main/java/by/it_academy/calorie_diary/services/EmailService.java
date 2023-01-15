package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.services.api.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    private static final String CONFIRMATION_REGISTRATION_URL = "http://localhost:8080/api/v1/users/verify?token=";

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendRegistrationConfirmationEmail(String recipient, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject("Confirmation of registration");
        message.setText(CONFIRMATION_REGISTRATION_URL + token);

        javaMailSender.send(message);
    }
}
