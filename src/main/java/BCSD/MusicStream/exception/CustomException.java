package BCSD.MusicStream.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter
@Builder
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus httpStatus;
    private String code;
    private String message;
}