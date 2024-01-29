package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByAnimalName(String animalName);
    Optional<Animal> findByAnimalId(int animalId);
}