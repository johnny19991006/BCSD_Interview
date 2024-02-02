package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserDTO {
    private String username;
    private String password;
    private String email;

    public UpdateUserDTO (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
