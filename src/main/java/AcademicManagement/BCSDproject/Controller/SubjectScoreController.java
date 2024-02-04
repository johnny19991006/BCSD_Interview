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
        return service.createSubjectScore(subjectScore, subjectScore.getSubjectName());
    }

    @GetMapping("")
    public List<SubjectScore> findAllSubjectScore()
    {
        return service.findAllSubjectScore();
    }

    @GetMapping("/{subjectName}/{studentId}")
    public SubjectScore findSubjectScore(@PathVariable String subjectName, @PathVariable String studentId)
    {
        return service.findSubjectScore(subjectName, studentId);
    }

    @PutMapping("/{subjectName}/{studentId}")
    public SubjectScore updateSubjectScore(@RequestBody SubjectScore subjectScore,
                                      @PathVariable String subjectName,
                                           @PathVariable String studentId)
    {
        return service.updateSubjectScore(subjectScore, subjectName, studentId);
    }

    @DeleteMapping("/{subjectName}/{studentId}")
    public void deleteSubjectScore(@PathVariable String subjectName, @PathVariable String studentId)
    {
        service.deleteSubjectScore(subjectName, studentId);
    }

    @GetMapping("/Student/{studentId}")
    public List<SubjectScore> findStudentScoreByStudentId(@PathVariable String studentId)
    {
        return service.findStudentScoreByStudentId(studentId);
    }

}
