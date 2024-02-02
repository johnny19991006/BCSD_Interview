package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "options")
@NoArgsConstructor
@Getter
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private int optionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions questions;

    @Column(name = "content", nullable = false)
    private String content;

    public Options(int optionId, Questions questions, String content){
        this.optionId = optionId;
        this.questions = questions;
        this.content = content;
    }
}
