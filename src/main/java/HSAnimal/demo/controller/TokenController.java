package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.CreateAccessTokenResponseDTO;
import HSAnimal.demo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/{user_id}/token")
    public ResponseEntity<CreateAccessTokenResponseDTO> createNewAccessToken(@PathVariable String user_id) {
        String newAccessToken = tokenService.createNewAccessToken(user_id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDTO(newAccessToken));
    }
}
