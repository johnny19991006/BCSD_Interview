package BCSD.MusicStream.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String user_name;
    private String user_email;
    private String user_pw;
    private LocalDate birth_date;
    private Integer authority_id;
    @Builder
    User(Integer user_id, String user_name, String user_email, String user_pw, LocalDate birth_date, Integer authority_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.birth_date = birth_date;
        this.authority_id = authority_id;
    }
}
