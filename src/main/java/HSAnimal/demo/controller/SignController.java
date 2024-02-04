package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.CreateAccessTokenDto;
import HSAnimal.demo.DTO.UserDto;
import HSAnimal.demo.service.SignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {
    private final SignService signService;

    public SignController(SignService signService){
        this.signService = signService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("\"" + signService.signup(request) + "\" 회원가입이 완료되었습니다!");
    }

    @PostMapping("/login")
    public ResponseEntity<CreateAccessTokenDto> login(@RequestBody UserDto userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(signService.login(userDTO));
    }
}
