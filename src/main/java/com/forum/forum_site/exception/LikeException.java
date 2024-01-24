package com.forum.forum_site.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikeException extends RuntimeException{
    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        ALREADY_PRESS_LIKE(900, HttpStatus.BAD_REQUEST, "이미 좋아요가 눌러져 있습니다.."),
        LIKE_NOT_FOUND(901, HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다.");

        private final int errorCode;
        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type(int errorCode, HttpStatus httpStatus, String errorMessage) {
            this.errorCode = errorCode;
            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }
    }

    // 파일 예외 생성자
    public LikeException(LikeException.Type type) {
        super(type.errorMessage);
        this.errorCode = type.errorCode;
        this.httpStatus = type.httpStatus;
        this.errorMessage = type.errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
