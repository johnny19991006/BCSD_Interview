package bcsd.backend.project.pokku.exception;

public class CustomException extends RuntimeException{
    private String message;
    private String errorCode;
    private String hint;

    protected CustomException() {

    }

    public CustomException(String message, String errorCode, String hint){
        this.message = message;
        this.errorCode = errorCode;
        this.hint = hint;
    }
}
