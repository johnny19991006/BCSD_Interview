package Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
Create Table Semester
(
	semester_id INT PRIMARY KEY AUTO_INCREMENT,
	student_id VARCHAR(20),
    semester_year INT NOT NULL,
    semester_grade ENUM('1학년', '2학년', '3학년', '4학년', '초과학기') NOT NULL,
    semester ENUM('1학기', '2학기', '여름학기', '겨울학기'),
    FOREIGN KEY (student_id) references Student(student_id)
);
*/
@NoArgsConstructor
@Getter
@Entity // JPA 사용 시 CRUD 작업 수행 가능
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "semester_id", length = 20)
    private int semesterId;

    @ManyToOne // 외래키 지정, 학생 아이디로 구분
    @JoinColumn(name = "student_id")
    private Student student_id;

    @Column(name = "semester_year", nullable = false)
    private int semesterYear;

    @Enumerated(EnumType.STRING) // enum을 DB 내부에 어떤 형태로 저장할지? -> String
    @Column(name = "semester_grade", nullable = false)
    private SemesterGradeEnum semesterGradeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semesterEnum;

    @Builder
    public Semester(int semesterId, Student student_id, int semesterYear,
                    SemesterGradeEnum semesterGradeEnum, SemesterEnum semesterEnum)
    {
        this.semesterId = semesterId;
        this.student_id = student_id;
        this.semesterYear = semesterYear;
        this.semesterGradeEnum = semesterGradeEnum;
        this.semesterEnum = semesterEnum;
    }
}
