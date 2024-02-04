package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    @Query("SELECT q.weight FROM options o JOIN o.questions q WHERE o.optionId = :optionId")
    Integer findWeightByOptionId(@Param("optionId") int optionId);
}
