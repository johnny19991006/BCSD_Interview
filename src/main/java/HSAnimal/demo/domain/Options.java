package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "options")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private int optionId;

    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Column(name = "content", nullable = false, length = 255)
    private String content;
}