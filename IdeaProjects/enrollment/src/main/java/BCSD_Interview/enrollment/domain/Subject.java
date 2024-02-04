package BCSD_Interview.enrollment.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name = "subject_record")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id", updatable = false, unique = true, nullable = false)
    private Long subject_id;

    @Column(name = "academic_year", nullable = false)
    private Integer academic_year;

    @Column(name = "semester", nullable = false)
    private String semester;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "subject_code", nullable = false)
    private String subject_code;

    @Column(name = "subject_name", nullable = false)
    private String subject_name;

    @Column(name = "class_num", nullable = false)
    private Integer class_num;

    @Column(name = "professor", nullable = false)
    private String professor;

    @Column(name = "completion_division", nullable = false)
    private String completion_division;

    @Column(name = "credit", nullable = false)
    private Integer credit;

    @Column(name = "mark", nullable = false)
    private String mark;

    @Column(name = "lecture_num", nullable = false)
    private Integer lecture_num;

    @Column(name = "lecture_time", nullable = false)
    private String lecture_time;

    @Column(name = "repetition", nullable = false)
    private String repetition;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    public void setAcademic_Year(Integer academic_year) {
        this.academic_year = academic_year;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setSubject_code(String subject_code) {
        this.semester = subject_code;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setClass_num(Integer class_num) {
        this.class_num = class_num;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCompletion_division(String completion_division) {
        this.completion_division = completion_division;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setLecture_num(Integer lecture_num) {
        this.lecture_num = lecture_num;
    }

    public void setLecture_time(String lecture_time) {
        this.lecture_time = lecture_time;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public void setStudent(Long user_id) {
        this.user_id = user_id;
    }
}
