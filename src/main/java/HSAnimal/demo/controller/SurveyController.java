package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserKeywordsDto;
import HSAnimal.demo.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveOptionList(@RequestBody List<UserKeywordsDto> userKeywordsList, @PathVariable String user_id) {
        surveyService.saveOptions(userKeywordsList, user_id);
        return ResponseEntity.status(HttpStatus.OK).body("키워드가 등록되었습니다");
    }
}
