package AcademicManagement.BCSDproject.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

import java.time.LocalDateTime;
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
);
*/

@NoArgsConstructor // AllArgsConstructor
@Getter // 도메인 내 Setter 사용 지양
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "student_id", length = 20, nullable = false) // 학생이 사용 할 아이디
    private String studentId;

    @Column(name = "student_pw", length = 100, nullable = false) // 학생이 사용 할 비밀번호
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

    @Column(name = "update_at") // 업데이트 시간
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "student") // 학생은 여러 학기를 가질 수 있음, 외래키
    private List<Semester> studentSemesterAll;

    @Builder
    public Student(String studentId, String studentPw, String studentName, String studentMajor,
                   int studentGrade, int studentSemester, String studentAttend, LocalDateTime updateAt)
    {
        this.studentId = studentId;
        this.studentPw = studentPw;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentGrade = studentGrade;
        this.studentSemester = studentSemester;
        this.studentAttend = studentAttend;
        this.updateAt = updateAt;
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

    public void setStudentAttend(String studentAttend) {
        this.studentAttend = studentAttend;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
