package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "animal_keywords")
@NoArgsConstructor
@Getter
@Setter
public class AnimalKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ak_id", nullable = false)
    private int akId;

    @Column(name = "animal_id", nullable = false)
    private int animalId;

    @Column(name = "option_id", nullable = false)
    private int optionId;

    public AnimalKeywords(int akId, int animalId, int optionId){
        this.akId = akId;
        this.animalId = animalId;
        this.optionId = optionId;
    }
}
