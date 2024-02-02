package bcsd.backend.project.pokku.exception.NullValueException;

import lombok.Getter;

@Getter
public class NullValueException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public NullValueException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
