package HSAnimal.demo.repository;

import HSAnimal.demo.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Questions findByQuestionId(int questionId);
}