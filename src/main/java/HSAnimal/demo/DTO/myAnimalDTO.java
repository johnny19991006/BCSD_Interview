package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class myAnimalDTO {
    private int animalId;
    private String animalName;
    private int matchScore;

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        myAnimalDTO myAnimalDTO = (myAnimalDTO) o;
        return animalId == myAnimalDTO.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId);
    }
}

