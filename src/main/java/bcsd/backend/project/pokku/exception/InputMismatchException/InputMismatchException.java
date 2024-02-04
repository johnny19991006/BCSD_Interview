package bcsd.backend.project.pokku.exception.InputMismatchException;

import lombok.Getter;

@Getter
public class InputMismatchException extends RuntimeException{
    private int errorCode;
    private String message;
    private String hint;

    public InputMismatchException(String message, String hint, int errorCode){
        this.message = message;
        this.hint = hint;
        this.errorCode = errorCode;
    }
}
