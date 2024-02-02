package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Options;
import HSAnimal.demo.domain.UserKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserKeywordsRepository extends JpaRepository<UserKeywords, Long> {
    List<UserKeywords> findAllByUserId(String userId);
    Optional<UserKeywords> findByUkId(int ukId);
    Optional<UserKeywords> findByUserIdAndOptionId(String userId, int optionId);
}
