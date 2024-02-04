package AcademicManagement.BCSDproject.Domain;

import AcademicManagement.BCSDproject.Enum.SemesterEnum;
import AcademicManagement.BCSDproject.Enum.SemesterGradeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Create Table Semester
(
	semester_id INT PRIMARY KEY AUTO_INCREMENT,
	student_id VARCHAR(20),
    semester_year INT NOT NULL,
    semester_grade ENUM('FRESHMAN', 'SOPHOMEORE', 'JUNIOR', 'SENIOR') NOT NULL, // DB 연결 간 문제가 생겨서 수
    semester ENUM('FIRST', 'SECOND', 'SUMMER', 'WINTER'), // 위와 동일
    semester_credit INT NOT NULL, // 학기 상 학점 필드 추가
    semester_score FLOAT NOT NULL, // 학기 상 성적 필드 추가
    semester_major_credit INT NOT NULL, // 학생 성적 정보에 계산이 필요하여 미리 추가했습니다.
    semester_major_score FLOAT NOT NULL, //
    semester_general_credit INT NOT NULL, //
    semester_general_score FLOAT NOT NULL // 위와 동일
    FOREIGN KEY (student_id) references Student(student_id)
);
*/
@NoArgsConstructor
@Getter
@Setter
@Entity // JPA 사용 시 CRUD 작업 수행 가능
@Table(name = "Semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "semester_id", length = 20, nullable = false)
    private int semesterId;

    @Column(name = "student_id", insertable = false, updatable = false)
    private String studentId;

    @Column(name = "semester_year", nullable = false)
    private Integer semesterYear;

    @Enumerated(EnumType.STRING) // enum을 DB 내부에 어떤 형태로 저장할지? -> String
    @Column(name = "semester_grade", nullable = false)
    private SemesterGradeEnum semesterGradeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semesterEnum;

    @Column(name = "semester_credit")
    private Integer semesterCredit;

    @Column(name = "semester_score")
    private Float semesterScore;

    @Column(name = "semester_major_credit")
    private Integer semesterMajorCredit;

    @Column(name = "semester_major_score")
    private Float semesterMajorScore;

    @Column(name = "semester_general_credit")
    private Integer semesterGeneralCredit;

    @Column(name = "semester_general_score")
    private Float semesterGeneralScore;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore // Semester 출력시 student 항목 삭제 목적
    private Student student;

    @Builder
    public Semester(int semesterId, String studentId, Integer semesterYear,
                    SemesterGradeEnum semesterGradeEnum, SemesterEnum semesterEnum,
                    Integer semesterCredit, Float semesterScore, Integer semesterMajorCredit,
                    Float semesterMajorScore, Integer semesterGeneralCredit, Float semesterGeneralScore)
    {
        this.semesterId = semesterId;
        this.studentId = studentId;
        this.semesterYear = semesterYear;
        this.semesterGradeEnum = semesterGradeEnum;
        this.semesterEnum = semesterEnum;
        this.semesterCredit = semesterCredit;
        this.semesterScore = semesterScore;
        this.semesterMajorCredit = semesterMajorCredit;
        this.semesterMajorScore = semesterMajorScore;
        this.semesterGeneralCredit = semesterGeneralCredit;
        this.semesterGeneralScore = semesterGeneralScore;
    }

    public Semester(Semester semester) {
        this.semesterId = semester.getSemesterId();
        this.studentId = semester.getStudentId();
        this.semesterYear = semester.getSemesterYear();
        this.semesterGradeEnum = semester.getSemesterGradeEnum();
        this.semesterEnum = semester.getSemesterEnum();
        this.semesterCredit = semester.getSemesterCredit();
        this.semesterScore = semester.getSemesterScore();
        this.semesterMajorCredit = semester.getSemesterMajorCredit();
        this.semesterMajorScore = semester.getSemesterMajorScore();
        this.semesterGeneralCredit = semester.getSemesterGeneralCredit();
        this.semesterGeneralScore = semester.getSemesterGeneralScore();
    }
}
