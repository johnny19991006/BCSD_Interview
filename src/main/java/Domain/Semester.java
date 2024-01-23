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
@Entity
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "semester_id", length = 20)
    private String semesterId;

    @Column(name = "student_id", length = 20)
    private String studentId;

    @Column(name = "semester_year", nullable = false)
    private int semesterYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester_grade", nullable = false)
    private SemesterGradeEnum semesterGradeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semesterEnum;

    @Builder
    public Semester(String semesterId, String studentId, int semesterYear,
                    SemesterGradeEnum semesterGradeEnum, SemesterEnum semesterEnum)
    {
        this.semesterId = semesterId;
        this.studentId = studentId;
        this.semesterYear = semesterYear;
        this.semesterGradeEnum = semesterGradeEnum;
        this.semesterEnum = semesterEnum;
    }
}
