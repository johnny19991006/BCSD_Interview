package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "weight", nullable = true)
    private int weight;
}