package BCSD.MusicStream.dto.member;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class SignUpMemberDTO {
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Integer authorityId;
}
