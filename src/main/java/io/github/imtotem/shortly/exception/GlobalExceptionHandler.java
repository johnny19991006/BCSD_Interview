package io.github.imtotem.shortly.exception;

import io.github.imtotem.shortly.dto.Error;
import io.github.imtotem.shortly.mapper.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorMapper mapper;

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Error> handleUserException(CustomException e) {
        return ResponseEntity
                .status(HttpStatus.valueOf(e.getStatus()))
                .body(mapper.toError(e));
    }
}
