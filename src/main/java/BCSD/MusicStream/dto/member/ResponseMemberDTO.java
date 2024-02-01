package BCSD.MusicStream.dto.member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ResponseMemberDTO {
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String authorityName;
}
