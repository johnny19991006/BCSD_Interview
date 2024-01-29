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
}
