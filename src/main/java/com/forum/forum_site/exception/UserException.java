package com.forum.forum_site.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException{
    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        ALREADY_EXIST_USERNAME(600, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
        WRONG_PASSWORD(601,HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),
        NOT_FOUND_MEMBER(602, HttpStatus.NOT_FOUND, "회원 정보가 없습니다.");

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
    public UserException(Type type) {
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
