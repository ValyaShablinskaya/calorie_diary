package by.it_academy.calorie_diary.services.exception;

import org.springframework.http.HttpStatus;

public class UserAuthenticationProcessingException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

    public UserAuthenticationProcessingException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
