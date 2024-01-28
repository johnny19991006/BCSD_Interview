package io.github.imtotem.shortly.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "잘못된 요청입니다."),

    //401 BAD_CREDENTIAL
    WRONG_PASSWORD(401, "비밀번호가 일치하지 않습니다."),

    //404 NOT_FOUND 잘못된 리소스 접근
    EMAIL_NOT_FOUND(404, "이메일을 찾을 수 없습니다."),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    URL_NOT_FOUND(404, "URL을 찾을 수 없습니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_EMAIL(409, "이미 존재하는 이메일입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.");

    private final int status;
    private final String message;
}
