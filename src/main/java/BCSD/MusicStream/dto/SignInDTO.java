package BCSD.MusicStream.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SignInDTO {
    private String userEmail;
    private String password;
}
