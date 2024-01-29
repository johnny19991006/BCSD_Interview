package HSAnimal.demo.repository;

import HSAnimal.demo.domain.AnimalKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalKeywordsRepository extends JpaRepository<AnimalKeywords, Long> {
    List<AnimalKeywords> findAllByAnimalId(int animalId);
    List<AnimalKeywords> findAllByOptionId(int optionId);
}
