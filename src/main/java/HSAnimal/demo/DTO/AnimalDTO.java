package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class AnimalDTO {
    private int animalId;
    private String animalName;
    private int matchScore;    // 추천 순위에 사용할 동물의 가중치 합

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDTO animalDTO = (AnimalDTO) o;
        return animalId == animalDTO.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId);
    }
}

