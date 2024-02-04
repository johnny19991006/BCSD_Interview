package HSAnimal.demo.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TOKEN_NOT_FOUND(401, HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    WRONG_TYPE_TOKEN(401,HttpStatus.UNAUTHORIZED,"잘못된 토큰 정보입니다."),
    EXPIRED_TOKEN(401,HttpStatus.UNAUTHORIZED,"만료된 토큰 정보입니다."),
    UNSUPPORTED_TOKEN(401,HttpStatus.UNAUTHORIZED,"지원하지 않는 토큰 방식입니다."),
    ACCESS_DENIED(401,HttpStatus.UNAUTHORIZED,"알 수 없는 이유로 요청이 거절되었습니다."),

    NOT_ALLOWED_AUTHORITY(401,HttpStatus.FORBIDDEN, "관리자만 접근할 수 있습니다."),

    ACCOUNT_ALREADY_EXISTS(401,HttpStatus.UNAUTHORIZED,"이미 존재하는 아이디입니다."),
    EMAIL_ALREADY_EXISTS(401,HttpStatus.UNAUTHORIZED,"이미 등록된 이메일입니다."),

    ACCOUNT_NOT_FOUND(401,HttpStatus.UNAUTHORIZED,"존재하지 않는 아이디입니다."),
    WRONG_PASSWORD(401,HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다."),

    EXCEPTION(401,HttpStatus.UNAUTHORIZED,"기타 예외");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(int code, HttpStatus httpStatus, String message){
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
