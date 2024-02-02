package bcsd.backend.project.pokku.exception.NoSuchDataException;

import lombok.Getter;

@Getter
public class NoSuchDataException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public NoSuchDataException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
