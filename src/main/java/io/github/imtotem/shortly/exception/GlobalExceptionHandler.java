package io.github.imtotem.shortly.exception;

import io.github.imtotem.shortly.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    protected ResponseEntity<Error> handleUserException(UserException e) {
        return ResponseEntity
                .status(HttpStatus.valueOf(e.getStatus()))
                .body(
                    Error.builder()
                        .status(e.getStatus())
                        .message(e.getMessage())
                        .build()
                );
    }
}
