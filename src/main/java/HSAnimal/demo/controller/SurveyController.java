package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserKeywordsDto;
import HSAnimal.demo.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping("/{user_id}/survey")
    public List<String> showQuestionList() {
        return surveyService.getQuestionList();
    }

    @PostMapping("/{user_id}/survey")
    public String saveOptionList(@RequestBody List<UserKeywordsDto> userKeywordsList, @PathVariable String user_id) {
        surveyService.saveOptions(userKeywordsList, user_id);
        return "키워드가 등록되었습니다.\n/{user_id}/match 링크로 리디랙션";
    }
}
