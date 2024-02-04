package AcademicManagement.BCSDproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.datatransfer.FlavorEvent;

/*
Create Table Student_Grade_Information
(
	student_id VARCHAR(20) PRIMARY KEY,
    total_credit INT NOT NULL,
    total_major_credit INT NOT NULL,
    avg_major_score FLOAT NOT NULL,
    total_general_credit INT NOT NULL,
    avg_general_score FLOAT NOT NULL,
    total_all_credit INT NOT NULL,
    avg_all_score FLOAT NOT NULL,
    FOREIGN KEY (student_id) references Student(student_id) // 학기 정보 삭제했음
);
*/

@NoArgsConstructor // AllArgsConstructor
@Setter
@Getter // 도메인 내 Setter 사용 지양
@Entity
@Table(name = "Student_Grade_Information")
public class StudentGradeInformation {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "total_credit", nullable = false)
    private Integer totalCredit;

    @Column(name = "total_major_credit", nullable = false)
    private Integer totalMajorCredit;

    @Column(name = "avg_major_score", nullable = false)
    private Float avgMajorScore;

    @Column(name = "total_general_credit", nullable = false)
    private Integer totalGeneralCredit;

    @Column(name = "avg_general_score", nullable = false)
    private Float avgGeneralScore;

    @Column(name = "total_all_credit", nullable = false)
    private Integer totalAllCredit;

    @Column(name = "avg_all_score", nullable = false)
    private Float avgAllScore;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    @Builder
    public StudentGradeInformation(String studentId, Integer totalCredit, Integer totalMajorCredit,
                                   Float avgMajorScore, Integer totalGeneralCredit, Float avgGeneralScore,
                                   Integer totalAllCredit, Float avgAllScore)
    {
        this.studentId = studentId;
        this.totalCredit = totalCredit;
        this.totalMajorCredit = totalMajorCredit;
        this.avgMajorScore = avgMajorScore;
        this.totalGeneralCredit = totalGeneralCredit;
        this.avgGeneralScore = avgGeneralScore;
        this.totalAllCredit = totalAllCredit;
        this.avgAllScore = avgAllScore;
    }
}
