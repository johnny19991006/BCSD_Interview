package HSAnimal.demo.repository;

import HSAnimal.demo.domain.UserKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordsRepository extends JpaRepository<UserKeywords, Long> {
    List<UserKeywords> findAllByUserId(String userId);
}
