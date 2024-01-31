package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {

    private String accessToken;
    private String refreshToken;

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
