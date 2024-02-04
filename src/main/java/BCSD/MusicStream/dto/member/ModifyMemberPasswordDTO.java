package BCSD.MusicStream.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ModifyMemberPasswordDTO {
    @NotBlank(message = "Password cannot be blank")
    private String origin;

    @NotBlank(message = "Password cannot be blank")
    private String change;
}
