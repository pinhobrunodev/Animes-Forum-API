package com.pinhobrunodev.animesforum.resources.exceptions;

import com.pinhobrunodev.animesforum.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Resource not found");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    protected ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Database Exception");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InsufficientNumberOfGenresException.class)
    protected ResponseEntity<StandardError> database(InsufficientNumberOfGenresException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError error = new StandardError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Number of Genders Exception");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();
        error.setMoment(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Validation Error");
        error.setPath(request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.add(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new OAuthCustomError("Forbidden", e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new OAuthCustomError("Unauthorized", e.getMessage()));
    }

}
