package BCSD.MusicStream.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomErrorCodeException.class)
    protected ResponseEntity<CustomErrorResponseEntity> handleCustomException(CustomErrorCodeException e){
        return CustomErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    protected ResponseEntity<CustomErrorResponseEntity> handleCustomException(CustomException e){
        return CustomErrorResponseEntity.toResponseEntity(e.getHttpStatus(), e.getCode(), e.getMessage());
    }
}