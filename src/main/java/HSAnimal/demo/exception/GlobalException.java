package HSAnimal.demo.exception;

import HSAnimal.demo.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<String> signupAccountException (AccountAlreadyExistsException e){
        return ResponseEntity.badRequest().body(ErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> signupEmailException (EmailAlreadyExistsException e){
        return ResponseEntity.badRequest().body(ErrorCode.EMAIL_ALREADY_EXISTS.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> LoginAccountException (AccountNotFoundException e){
        return ResponseEntity.badRequest().body(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> LoginEmailException (WrongPasswordException e){
        return ResponseEntity.badRequest().body(ErrorCode.WRONG_PASSWORD.getMessage());
    }

    // 토큰 인증 관련 에러는 RestControllerAdvice 이전에 처리되어서 사용자 정의 예외처리는 사용 X
    @ExceptionHandler
    public ResponseEntity<String> authorityException (NotAllowedAuthorityException e){
        return ResponseEntity.badRequest().body(ErrorCode.NOT_ALLOWED_AUTHORITY.getMessage());
    }
}
