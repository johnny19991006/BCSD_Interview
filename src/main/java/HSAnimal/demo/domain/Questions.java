package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity(name = "questions")
@NoArgsConstructor
@Getter
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "weight")
    private int weight;

    @OneToMany(mappedBy = "questions")
    private List<Options> options;

    public Questions(int questionId, String content, int weight, List<Options> options){
        this.questionId = questionId;
        this.content = content;
        this.weight = weight;
        this.options = options;
    }
}
