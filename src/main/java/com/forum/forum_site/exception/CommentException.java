package com.forum.forum_site.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    // 파일 예외 유형을 정의하는 열거형을 내부 클래스로 정의
    public enum Type {
        NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "댓글이 없습니다."),
        NOT_AUTHORITY_UPDATE_COMMENT(HttpStatus.FORBIDDEN, "댓글을 업데이트할 권한이 없습니다."),
        NOT_AUTHORITY_DELETE_COMMENT(HttpStatus.FORBIDDEN, "댓글을 삭제할 권한이 없습니다.");

        private final HttpStatus httpStatus;
        private final String errorMessage;

        Type( HttpStatus httpStatus, String errorMessage) {
            this.httpStatus = httpStatus;
            this.errorMessage = errorMessage;
        }
    }
    // 파일 예외 생성자
    public CommentException(Type type) {
        super(type.errorMessage);
        this.httpStatus = type.httpStatus;
        this.errorMessage = type.errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
