package bcsd.backend.project.pokku.exception;

import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyExceptionModel;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchException;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchExceptionModel;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataExceptionModel;
import bcsd.backend.project.pokku.exception.NotSupportException.NotSupportException;
import bcsd.backend.project.pokku.exception.NotSupportException.NotSupportExceptionModel;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueExceptionModel;
import bcsd.backend.project.pokku.exception.UnknownException.UnknownException;
import bcsd.backend.project.pokku.exception.UnknownException.UnknownExceptionModel;
import com.google.protobuf.NullValue;
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

    @ExceptionHandler(NullValueException.class)
    public final ResponseEntity<Object> handleNullValueException(NullValueException ex){
        NullValueExceptionModel exceptionResponse = new NullValueExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotSupportException.class)
    public final ResponseEntity<Object> handleNotSupportException(NotSupportException ex){
        NotSupportExceptionModel exceptionResponse = new NotSupportExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnknownException.class)
    public final ResponseEntity<Object> handleNotSupportException(UnknownException ex){
        UnknownExceptionModel exceptionResponse = new UnknownExceptionModel(ex.getErrorCode(), ex.getMessage(), ex.getHint());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
