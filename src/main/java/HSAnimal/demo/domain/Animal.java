package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Entity(name = "animals")
@Getter
@Setter
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false)
    private int animalId;

    @Column(name = "animal_name", nullable = false, unique = true)
    private String animalName;

    @Column(name = "animal_pref")
    private String animalPref;

    public Animal(int animalId, String animalName, String animalPref){
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalPref = animalPref;
    }

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
