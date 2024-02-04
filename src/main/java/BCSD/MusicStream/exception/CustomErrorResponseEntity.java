package BCSD.MusicStream.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class CustomErrorResponseEntity {
    private int status;
    private String name;
    private String code;
    private String message;

    public static ResponseEntity<CustomErrorResponseEntity> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CustomErrorResponseEntity.builder()
                        .status(e.getHttpStatus().value())
                        .name(e.getHttpStatus().name())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }

    public static ResponseEntity<CustomErrorResponseEntity> toResponseEntity(HttpStatus httpStatus, String code, String message){
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorResponseEntity.builder()
                        .status(httpStatus.value())
                        .name(httpStatus.name())
                        .code(code)
                        .message(message)
                        .build());
    }
}