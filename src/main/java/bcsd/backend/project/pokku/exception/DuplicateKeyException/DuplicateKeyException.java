package bcsd.backend.project.pokku.exception.DuplicateKeyException;

import lombok.Getter;

@Getter
public class DuplicateKeyException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public DuplicateKeyException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
