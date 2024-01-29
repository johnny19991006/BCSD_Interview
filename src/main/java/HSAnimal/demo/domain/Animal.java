package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Entity(name = "animals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false)
    private int animalId;

    @Column(name = "animal_name", nullable = false, length = 255, unique = true)
    private String animalName; //유저이름

    @Column(name = "animal_pref", nullable = false, length = 255)
    private String animalPref; //비밀번호

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return animalId == animal.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId);
    }
}
