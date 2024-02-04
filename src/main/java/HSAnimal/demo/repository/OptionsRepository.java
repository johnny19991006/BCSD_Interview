package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options, Long> {
    Optional<Options> findByOptionId(int optionId);
}
