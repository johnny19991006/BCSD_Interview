package AcademicManagement.BCSDproject.Controller;

import AcademicManagement.BCSDproject.Domain.Subject;
import AcademicManagement.BCSDproject.Service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Subject")
public class SubjectController {

    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service)
    {
        this.service = service;
    }

    @PostMapping("")
    public Subject createSubject(@RequestBody Subject subject)
    {
        return service.createSubject(subject);
    }

    @GetMapping("")
    public List<Subject> findAllSubject()
    {
        return service.findAllSubject();
    }

    @GetMapping("/{subjectName}")
    public Subject findById(@PathVariable String subjectName)
    {
        return service.findById(subjectName);
    }

    @PutMapping("/{subjectName}")
    public Subject updateSubject(@RequestBody Subject subject, @PathVariable String subjectName)
    {
        return service.updateSubject(subject, subjectName);
    }

    @DeleteMapping("/{subjectName}")
    public void deleteSubject(@PathVariable String subjectName)
    {
        service.deleteSubject(subjectName);
    }
}
