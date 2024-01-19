package BCSD.MusicStream.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class SignUpDTO {
    private String user_name;
    private String user_email;
    private String user_pw;
    private LocalDate birth_date;
    private Boolean authority_type;
}
