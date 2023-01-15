package by.it_academy.calorie_diary.controllers.handler;

import by.it_academy.calorie_diary.services.dto.ResponseError;
import by.it_academy.calorie_diary.services.exception.AccessIsDeniedException;
import by.it_academy.calorie_diary.services.exception.UserAuthenticationProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseError handleEIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessIsDeniedException.class)
    public ResponseError handleAccessIsDeniedException(AccessIsDeniedException e) {
        return new ResponseError(HttpStatus.FORBIDDEN.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseError handleAccessIsDeniedException(AccessDeniedException e) {
        return new ResponseError(HttpStatus.FORBIDDEN.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    public ResponseError handleEntityExistsException(EntityExistsException e) {
        return new ResponseError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserAuthenticationProcessingException.class)
    public ResponseError handleUserAuthenticationProcessingException(UserAuthenticationProcessingException e) {
        return new ResponseError(HttpStatus.UNAUTHORIZED.toString(), e.getMessage());
    }
}
