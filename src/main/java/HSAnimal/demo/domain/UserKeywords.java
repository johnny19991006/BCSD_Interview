package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "user_keywords")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uk_id", nullable = false)
    private Long ukId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "option_id", nullable = false)
    private int optionId;
}