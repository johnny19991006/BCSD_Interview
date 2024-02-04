package HSAnimal.demo.controller;


import HSAnimal.demo.DTO.CreateAccessTokenDto;
import HSAnimal.demo.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TokenController {

    private final TokenService tokenService;

    public TokenController (TokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping("/{user_id}/token")
    public ResponseEntity<CreateAccessTokenDto> recreateAccessToken(@PathVariable String user_id) {
        CreateAccessTokenDto newAccessToken = tokenService.recreateAccessToken(user_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccessToken);
    }
}
