package BCSD.MusicStream.dto.member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ModifyMemberDTO {
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Integer authorityId;
}
