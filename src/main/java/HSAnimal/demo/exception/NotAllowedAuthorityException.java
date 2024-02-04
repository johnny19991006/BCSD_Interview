package HSAnimal.demo.exception;

public class NotAllowedAuthorityException extends RuntimeException{
    public NotAllowedAuthorityException (String message){
        super(message);
    }
}
