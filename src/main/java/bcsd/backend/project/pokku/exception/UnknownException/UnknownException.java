package bcsd.backend.project.pokku.exception.UnknownException;

import lombok.Getter;

@Getter
public class UnknownException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public UnknownException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
