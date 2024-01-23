package BCSD_Interview.enrollment.DTO;

import BCSD_Interview.enrollment.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {
    private String username;
    private String password;
    private String department;
    private Long grade;
    private String registrable_grade;
    private List<String> roles = new ArrayList<>();

    public User toEntity(String encodedPassword, List<String> roles) {
        return  User.builder()
                .username(username)
                .password(encodedPassword)
                .department(department)
                .grade(grade)
                .registrable_grade(registrable_grade)
                .roles(roles)
                .build();
    }
}
