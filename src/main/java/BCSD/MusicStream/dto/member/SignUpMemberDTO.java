package BCSD.MusicStream.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class SignUpMemberDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "BirthDate cannot be null")
    private LocalDate birthDate;

    @NotNull(message = "AuthorityID cannot be null")
    private Integer authorityId;
}
