package io.github.imtotem.shortly.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final int status;
    private final String message;

    public UserException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}
