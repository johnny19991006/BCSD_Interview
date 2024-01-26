package AcademicManagement.BCSDproject.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
Create Table Student_Grade_Information
(
	student_id VARCHAR(20) PRIMARY KEY,
    semester_id INT,
    total_credit INT NOT NULL,
    total_major_credit INT NOT NULL,
    avg_major_score FLOAT NOT NULL,
    total_general_credit INT NOT NULL,
    avg_general_score FLOAT NOT NULL,
    total_all_credit INT NOT NULL,
    avg_all_score FLOAT NOT NULL,
    FOREIGN KEY (student_id) references Student(student_id),
    FOREIGN KEY (semester_id) references Semester(semester_id)
);
*/

@NoArgsConstructor // AllArgsConstructor
@Getter // 도메인 내 Setter 사용 지양
@Entity
@Table(name = "Student_Grade_Information")
public class StudentGradeInformation {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "semester_id")
    private int semesterId;

    @Column(name = "total_credit", nullable = false)
    private int totalCredit;

    @Column(name = "total_major_credit", nullable = false)
    private int totalMajorCredit;

    @Column(name = "avg_major_score", nullable = false)
    private float avgMajorScore;

    @Column(name = "total_general_credit", nullable = false)
    private int totalGeneralCredit;

    @Column(name = "avg_general_score", nullable = false)
    private float avgGeneralScore;

    @Column(name = "total_all_credit", nullable = false)
    private int totalAllCredit;

    @Column(name = "avg_all_score", nullable = false)
    private float avgAllScore;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Builder
    public StudentGradeInformation(String studentId, int semesterId, int totalCredit, int totalMajorCredit,
                                   float avgMajorScore, int totalGeneralCredit, float avgGeneralScore,
                                   int totalAllCredit, float avgAllScore)
    {
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.totalCredit = totalCredit;
        this.totalMajorCredit = totalMajorCredit;
        this.avgMajorScore = avgMajorScore;
        this.totalGeneralCredit = totalGeneralCredit;
        this.avgGeneralScore = avgGeneralScore;
        this.totalAllCredit = totalAllCredit;
        this.avgAllScore = avgAllScore;
    }
}
