package AcademicManagement.BCSDproject.Controller;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.List;

@RestController
@RequestMapping("/Semester")
public class SemesterController {
    private final SemesterService service;

    @Autowired
    public SemesterController(SemesterService service)
    {
        this.service = service;
    }

    @PostMapping("")
    public Semester createSemester(@RequestBody Semester semester)
    {
        return service.createSemester(semester);
    }

    @GetMapping("")
    public List<Semester> findAllSemester()
    {
        return service.findAllSemester();
    }

    @GetMapping("/{semesterId}")
    public Semester findSemester(@PathVariable int semesterId)
    {
        return service.findSemester(semesterId);
    }

    @PutMapping("/{semesterId}")
    public Semester updateSemester(@RequestBody Semester semester, @PathVariable int semesterId)
    {
        return service.updateSemester(semester, semesterId);
    }

    @DeleteMapping("/{semesterId}")
    public void deleteSemester(@PathVariable int semesterId)
    {
        service.deleteSemester(semesterId);
    }

    @GetMapping("/Student/{studentId}")
    public List<Semester> studentSemester(String studentId)
    {
        return service.studentSemester(studentId);
    }

}
