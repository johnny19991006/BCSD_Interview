package com.forum.forum_site.exception;

import org.springframework.http.HttpStatus;

public class MessageException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        ALREADY_EXIST_MESSAGEROOM(HttpStatus.BAD_REQUEST, "이미 쪽지 방이 존재합니다"),
        RECEIVER_NOT_FOUND(HttpStatus.NOT_FOUND, "받는 사람을 찾을 수 없습니다."),
        MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "메시지를 찾을 수 없습니다."),
        SENDER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보가 일치하지 않습니다.");

        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type(HttpStatus httpStatus, String errorMessage) {
            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }
    }

    // 파일 예외 생성자
    public MessageException(Type type) {
        super(type.errorMessage);
        this.httpStatus = type.httpStatus;
        this.errorMessage = type.errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
