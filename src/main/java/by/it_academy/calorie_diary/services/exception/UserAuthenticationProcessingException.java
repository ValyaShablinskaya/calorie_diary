package by.it_academy.calorie_diary.services.exception;

import org.springframework.http.HttpStatus;

public class UserAuthenticationProcessingException extends RuntimeException{
    private HttpStatus httpStatus;

    public UserAuthenticationProcessingException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
