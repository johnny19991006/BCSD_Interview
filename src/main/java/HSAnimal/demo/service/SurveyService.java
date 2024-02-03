package HSAnimal.demo.service;

import HSAnimal.demo.dto.UserKeywordsDto;
import HSAnimal.demo.domain.Questions;
import HSAnimal.demo.domain.UserKeywords;
import HSAnimal.demo.repository.QuestionsRepository;
import HSAnimal.demo.repository.UserKeywordsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    private final QuestionsRepository questionsRepository;
    private final UserKeywordsRepository userKeywordsRepository;

    public SurveyService(QuestionsRepository questionsRepository, UserKeywordsRepository userKeywordsRepository){
        this.questionsRepository = questionsRepository;
        this.userKeywordsRepository = userKeywordsRepository;
    }

    public List<String> getQuestionList(){
        List<Questions> questionsList = questionsRepository.findAll();
        return questionsList.stream()
                .map(Questions::getContent)
                .collect(Collectors.toList());
    }

    // 사용자의 키워드 저장하기
    public void saveOptions(List<UserKeywordsDto> userKeywordsList, String user_id) {
        for (UserKeywordsDto userKeywordsDTO : userKeywordsList) {
            UserKeywords userKeywords = UserKeywords.builder()
                    .userId(user_id)
                    .optionId(userKeywordsDTO.getOptionId())
                    .build();
            userKeywordsRepository.save(userKeywords);
        }
    }
}
