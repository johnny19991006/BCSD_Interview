package BCSD.MusicStream.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomErrorCodeException extends RuntimeException {
    ErrorCode errorCode;
}