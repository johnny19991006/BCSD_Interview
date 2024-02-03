package HSAnimal.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String email;
}
