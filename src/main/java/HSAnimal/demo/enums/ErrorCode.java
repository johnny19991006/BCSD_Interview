package HSAnimal.demo.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TOKEN_NOT_FOUND(401, HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    FORBIDDEN_REQUEST(401,HttpStatus.FORBIDDEN, "ADMIN 회원만 접근할 수 있습니다."),
    WRONG_TYPE_TOKEN(401,HttpStatus.UNAUTHORIZED,"잘못된 토큰 정보입니다."),
    EXPIRED_TOKEN(401,HttpStatus.UNAUTHORIZED,"만료된 토큰 정보입니다."),
    UNSUPPORTED_TOKEN(401,HttpStatus.UNAUTHORIZED,"지원하지 않는 토큰 방식입니다."),
    ACCESS_DENIED(401,HttpStatus.UNAUTHORIZED,"알 수 없는 이유로 요청이 거절되었습니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(int code, HttpStatus httpStatus, String message){
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
