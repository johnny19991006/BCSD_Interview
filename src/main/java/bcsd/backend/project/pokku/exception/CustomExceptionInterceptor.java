package bcsd.backend.project.pokku.exception;

import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyExceptionModel;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchException;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchExceptionModel;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchDataException.class)
    public final ResponseEntity<Object> handleNoSuchDataException(NoSuchDataException ex){
        NoSuchDataExceptionModel exceptionResponse = new NoSuchDataExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InputMismatchException.class)
    public final ResponseEntity<Object> handleInputMismatchException(InputMismatchException ex){
        InputMismatchExceptionModel exceptionResponse = new InputMismatchExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex){
        DuplicateKeyExceptionModel exceptionResponse = new DuplicateKeyExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
