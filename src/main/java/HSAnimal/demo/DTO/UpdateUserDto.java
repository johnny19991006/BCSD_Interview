package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserDto {
    private String username;
    private String password;
    private String email;

    public UpdateUserDto(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
