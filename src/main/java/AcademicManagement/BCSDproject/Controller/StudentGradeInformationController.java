package AcademicManagement.BCSDproject.Controller;

import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;
import AcademicManagement.BCSDproject.Service.StudentGradeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GradeInformation")
public class StudentGradeInformationController {
    private final StudentGradeInformationService service;

    @Autowired
    public StudentGradeInformationController(StudentGradeInformationService service){this.service = service;}

    @PostMapping("")
    public StudentGradeInformation createStudentGradeInformation(@RequestBody StudentGradeInformation studentGradeInformation)
    {
        return service.createStudentGradeInformation(studentGradeInformation);
    }

    @GetMapping("")
    public List<StudentGradeInformation> findAllGradeInformation()
    {
        return service.findAllGradeInformation();
    }

    @GetMapping("/{studentId}")
    public StudentGradeInformation findStudentGradeInformation(@PathVariable String studentId)
    {
        return service.findStudentGradeInformation(studentId);
    }

    @PutMapping("/{studentId}")
    public StudentGradeInformation updateStudentGradeInformation(@RequestBody StudentGradeInformation studentGradeInformation,
                                                                 @PathVariable String studentId)
    {
        return service.updateStudentGradeInformation(studentGradeInformation, studentId);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentGradeInformation(@PathVariable String studentId)
    {
        service.deleteStudentGradeInformation(studentId);
    }
}
