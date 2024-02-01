package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "user_keywords")
@Getter
@Setter
public class UserKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uk_id", nullable = false)
    private int ukId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "option_id", nullable = false)
    private int optionId;

    public UserKeywords(){}

    public UserKeywords(int ukId, String userId, int optionId){
        this.ukId = ukId;
        this.userId = userId;
        this.optionId = optionId;
    }
}
