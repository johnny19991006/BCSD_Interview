package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByAnimalName(String animalName); // 동물을 animal_name(String)으로 찾기 위한 메소드
}