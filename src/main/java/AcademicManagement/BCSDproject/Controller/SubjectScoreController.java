package AcademicManagement.BCSDproject.Controller;

import AcademicManagement.BCSDproject.Domain.SubjectScore;
import AcademicManagement.BCSDproject.Service.SubjectScoreService;
import AcademicManagement.BCSDproject.Service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Score")
public class SubjectScoreController {
    private final SubjectScoreService service;

    @Autowired
    public SubjectScoreController(SubjectScoreService service)
    {
        this.service = service;
    }

    @PostMapping("")
    public SubjectScore createSubjectScore(@RequestBody SubjectScore subjectScore)
    {
        return service.createSubjectScore(subjectScore);
    }

    @GetMapping("")
    public List<SubjectScore> findAllSubjectScore()
    {
        return service.findAllSubjectScore();
    }

    @GetMapping("/{subjectName}")
    public SubjectScore findSubjectScore(@PathVariable String subjectName)
    {
        return service.findSubjectScore(subjectName);
    }

    @PutMapping("/{subjectName}")
    public SubjectScore updateSubjectScore(@RequestBody SubjectScore subjectScore,
                                      @PathVariable String subjectName)
    {
        return service.updateSubjectScore(subjectScore, subjectName);
    }

    @DeleteMapping("/{subjectName}")
    public void deleteSubjectScore(@PathVariable String subjectName)
    {
        service.deleteSubjectScore(subjectName);
    }

}
