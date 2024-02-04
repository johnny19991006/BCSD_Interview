package AcademicManagement.BCSDproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
/*
   Create Table Student
(
	student_id VARCHAR(20) PRIMARY KEY NOT NULL,
    student_pw VARCHAR(100) NOT NULL,
    student_name VARCHAR(20) NOT NULL,
    student_major VARCHAR(20) NOT NULL,
    student_grade INT(1) NOT NULL,
    student_semester INT(2) NOT NULL,
    student_attend VARCHAR(10) NOT NULL,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    roles VARCAHR(10) NOT NULL // 역할은 따로 Student_roles 테이블 생성
);
update_at은 현재 빼두고, 추후에 수정시 추가
*/

@NoArgsConstructor // AllArgsConstructor
@Getter // 도메인 내 Setter 사용 지양
@Entity
@Table(name = "Student")
public class Student implements UserDetails {
    @Id
    @Column(name = "student_id", length = 20, nullable = false) // 학생이 사용 할 아이디
    private String studentId;

    @Column(name = "student_pw", length = 100, nullable = false) // 학생이 사용 할 비밀번호
    @JsonIgnore
    private String studentPw;

    @Column(name = "student_name", length = 20, nullable = false) // 학생 이름
    private String studentName;

    @Column(name = "student_major", length = 20, nullable = false) // 전공
    private String studentMajor;

    @Column(name = "student_grade", nullable = false) // 학년
    private int studentGrade;

    @Column(name = "student_semester", nullable = false)
    private int studentSemester;

    @Column(name = "student_attend", length = 10, nullable = false) // 학적 상황
    private String studentAttend;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "Student_roles",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "roles")
    private List<String> roles = new ArrayList<>();

    @Override // 회원 가입 구현을 위해
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return studentId;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return studentPw;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    //@OneToMany(mappedBy = "student") // 학생은 여러 학기를 가질 수 있음, 외래키
    //private List<Semester> studentSemesterAll;

    @Builder
    public Student(String studentId, String studentPw, String studentName, String studentMajor,
                   int studentGrade, int studentSemester, String studentAttend,
                   LocalDateTime updateAt, List<String> roles) {
        this.studentId = studentId;
        this.studentPw = studentPw;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentGrade = studentGrade;
        this.studentSemester = studentSemester;
        this.studentAttend = studentAttend;
        this.updateAt = updateAt;
        this.roles = roles;
    }
    public void setStudentPw(String studentPw) {
        this.studentPw = studentPw;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public void setStudentSemester(int studentSemester) {
        this.studentSemester = studentSemester;
    }

    public void setStudentAttend(String studentAttend) {this.studentAttend = studentAttend;}

    public void setUpdateAt(LocalDateTime updateAt) {this.updateAt = updateAt;}

    public void setRoles(List<String> roles) {this.roles = roles;}
}
