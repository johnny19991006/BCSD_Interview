package com.forum.forum_site.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FileException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        FILE_CAN_NOT_SAVE(HttpStatus.BAD_REQUEST, "파일 저장에 실패했습니다."),
        FILE_CAN_NOT_DELETE(HttpStatus.BAD_REQUEST, "파일 삭제에 실패했습니다."),
        FILE_CAN_NOT_UPLOAD(HttpStatus.BAD_REQUEST, "파일 업로드에 실패했습니다.");

        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type(HttpStatus httpStatus, String errorMessage) {

            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }
    }

    // 파일 예외 생성자
    public FileException(Type type) {
        super(type.errorMessage);
        this.httpStatus = type.httpStatus;
        this.errorMessage = type.errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
