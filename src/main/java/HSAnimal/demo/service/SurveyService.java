package HSAnimal.demo.service;

import HSAnimal.demo.DTO.UserKeywordsDTO;
import HSAnimal.demo.domain.Questions;
import HSAnimal.demo.domain.UserKeywords;
import HSAnimal.demo.repository.QuestionsRepository;
import HSAnimal.demo.repository.UserKeywordsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {
    private final QuestionsRepository questionRepository;
    private final UserKeywordsRepository userKeywordsRepository;

    public SurveyService(QuestionsRepository questionRepository, UserKeywordsRepository userKeywordsRepository){
        this.questionRepository = questionRepository;
        this.userKeywordsRepository = userKeywordsRepository;
    }

    // 질문 모두 불러오기
    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    // 사용자의 키워드 저장하기
    public String keywordsRegister(UserKeywordsDTO dto, String user_id) {
        UserKeywords userKeywords = UserKeywords.builder()
                .userId(user_id)
                .optionId(dto.getOptionId())
                .build();
        return userKeywordsRepository.save(userKeywords).getUserId();
    }


}