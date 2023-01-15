package by.it_academy.calorie_diary.controllers.handler;

import by.it_academy.calorie_diary.security.exception.JwtAuthenticationException;
import by.it_academy.calorie_diary.services.dto.ResponseError;
import by.it_academy.calorie_diary.services.exception.AccessIsDeniedException;
import by.it_academy.calorie_diary.services.exception.UserAuthenticationProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseError handleDataIntegrityViolationException(DataIntegrityViolationException e) {
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseError handleJwtAuthenticationException(JwtAuthenticationException e) {
        return new ResponseError(HttpStatus.UNAUTHORIZED.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseError handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return new ResponseError(HttpStatus.FORBIDDEN.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseError(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }
}
