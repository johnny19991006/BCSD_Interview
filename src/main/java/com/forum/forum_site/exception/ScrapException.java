package com.forum.forum_site.exception;

import org.springframework.http.HttpStatus;

public class ScrapException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        ALREADY_EXIST_SCRAP(HttpStatus.BAD_REQUEST, "이미 스크랩이 되어져 있습니다.."),
        SCRAP_NOT_FOUND(HttpStatus.NOT_FOUND, "스크랩을 찾을 수 없습니다.");

        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type(HttpStatus httpStatus, String errorMessage) {
            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }
    }

    // 파일 예외 생성자
    public ScrapException(ScrapException.Type type) {
        super(type.errorMessage);
        this.httpStatus = type.httpStatus;
        this.errorMessage = type.errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
