package BCSD_Interview.enrollment.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name = "subject_list")
public class SubjectList {
    @Id
    @GeneratedValue
    private Long subject_id;
    private String note;
    private String academic_year_semester;
    private String subject_code;
    private String subject_name;
    private Integer class_num;
    private String subject_classification_code;
    private String completion_division;
    private Integer credit;
    private Integer lecture_num;
    private String established_undergraduate_studies;
    private String registered_undergraduate_studies;
    private Integer grade;
    private String professor;
    private Integer limit;
    private String lecture_time;
    private String registrable_grade;
    private String evaluation;
}
