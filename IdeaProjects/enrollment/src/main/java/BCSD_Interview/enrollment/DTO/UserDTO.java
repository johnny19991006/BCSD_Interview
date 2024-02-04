package BCSD_Interview.enrollment.DTO;

import BCSD_Interview.enrollment.domain.User;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer user_id;
    private String username;
    private String password;
    private String department;
    private Integer grade;
    private String registrable_grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegistrableGrade() {
        return registrable_grade;
    }

    public void setRegistrable_grade(String registrable_grade) {
        this.registrable_grade = registrable_grade;
    }

    static public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .department(user.getDepartment())
                .grade(user.getGrade())
                .registrable_grade(user.getRegistrable_grade()).build();
    }

    public User toEntity() {
        User user = User.builder()
                .user_id(user_id)
                .username(username)
                .password(password)
                .department(department)
                .grade(grade)
                .build();
        user.settingRegistrable_Grade();
        return user;
    }
}
