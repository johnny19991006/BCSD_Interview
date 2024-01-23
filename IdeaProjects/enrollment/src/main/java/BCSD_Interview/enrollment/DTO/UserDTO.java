package BCSD_Interview.enrollment.DTO;

import BCSD_Interview.enrollment.domain.User;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long user_id;
    private String username;
    private String password;
    private String department;
    private Long grade;
    private String registrable_grade;

    static public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .department(user.getDepartment())
                .grade(user.getGrade())
                .registrable_grade(user.getRegistrable_grade()).build();
    }

    public User toEntity() {
        return User.builder()
                .user_id(user_id)
                .username(username)
                .password(password)
                .department(department)
                .grade(grade)
                .registrable_grade(registrable_grade).build();
    }
}
