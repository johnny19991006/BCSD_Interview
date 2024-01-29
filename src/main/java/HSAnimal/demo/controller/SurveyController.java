package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserKeywordsDTO;
import HSAnimal.demo.domain.Questions;
import HSAnimal.demo.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping("/{user_id}/survey")
    public List<String> questionsList() {
        List<Questions> questionsList = surveyService.getAllQuestions();
        List<String> contentList = new ArrayList<>();

        for (Questions question : questionsList) {
            String content = question.getContent();
            contentList.add(content);
        }
        return contentList;
    }

    @PostMapping("/{user_id}/survey")
    public String getOptionsList(@RequestBody List<UserKeywordsDTO> userKeywordsList,@PathVariable String user_id) {
        for (UserKeywordsDTO userKeywordsDTO : userKeywordsList) {
            surveyService.keywordsRegister(userKeywordsDTO, user_id);
        }
        return "/{user_id}/match 링크로 이동 하고싶은데..";
    }
}