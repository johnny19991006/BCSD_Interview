package bcsd.backend.project.pokku.exception.NotSupportException;

import lombok.Getter;

@Getter
public class NotSupportException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public NotSupportException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
