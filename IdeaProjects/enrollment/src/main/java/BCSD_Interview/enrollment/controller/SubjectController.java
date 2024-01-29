package BCSD_Interview.enrollment.controller;

import BCSD_Interview.enrollment.domain.Subject;
import BCSD_Interview.enrollment.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/")
    public Subject insertSubject(@RequestBody Subject subject) {
        return subjectService.insertSubject(subject);
    }

    @GetMapping("")
    public List<Subject> getAllSubject_Record() {
        return subjectService.getAllSubject_Record();
    }

    @GetMapping("/{subjectId}")
    public Subject getSubjectBySubjectId(@PathVariable Long subjectId) {
        return subjectService.getSubjectBySubjectId(subjectId);
    }

    @DeleteMapping("/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
