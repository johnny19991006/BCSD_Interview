package Domain;

/*
Create Table Subject
(
    subject_name VARCHAR(20) PRIMARY KEY NOT NULL,
    professor_name VARCHAR(20),
    category ENUM('전공', '교양') NOT NULL,
    credit INT(1) NOT NULL
);
*/
// subject_name은 문자열로 할 지, AUTO_INCREMENT를 이용한 INT 형으로 사용 할 지 한 번 생각 해보겠습니다.
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_name", length = 20, nullable = false)
    private String subjectName;

    @Column(name = "professor_name", length = 20)
    private String professorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CategoryEnum categoryEnum;

    @Column(name = "credit", nullable = false)
    private int credit;

    @OneToMany(mappedBy = "subject") // 과목에서 성적을 참조할 것? -> 할 수 있으면 좋겠음
    private List<SubjectScore> subjectScores = new ArrayList<>();

    @Builder
    public Subject(String subjectName, String professorName, CategoryEnum categoryEnum, int credit)
    {
        this.subjectName = subjectName;
        this.professorName = professorName;
        this.categoryEnum = categoryEnum;
        this.credit = credit;
    }
}
