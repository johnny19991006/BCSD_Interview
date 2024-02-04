package BCSD_Interview.enrollment.controller;

import BCSD_Interview.enrollment.domain.Subject;
import BCSD_Interview.enrollment.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{subjectId}/ay")
    public void updateAcademic_Year(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateAcademic_Year(subjectId, subject.getAcademic_year());
    }

    @PutMapping("/{subjectId}/semester")
    public void updateSemester(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateSemester(subjectId, subject.getSemester());
    }

    @PutMapping("/{subjectId}/grade")
    public void updateGrade(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateGrade(subjectId, subject.getGrade());
    }

    @PutMapping("/{subjectId}/sc")
    public void updateSubject_Code(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateSubject_Code(subjectId, subject.getSubject_code());
    }

    @PutMapping("/{subjectId}/sn")
    public void updateSubject_Name(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateSubject_Name(subjectId, subject.getSubject_name());
    }

    @PutMapping("/{subjectId}/cm")
    public void updateClass_Num(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateClass_Num(subjectId, subject.getClass_num());
    }

    @PutMapping("/{subjectId}/prof")
    public void updateProfessor(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateProfessor(subjectId, subject.getProfessor());
    }

    @PutMapping("/{subjectId}/cd")
    public void updateCompletion_Division(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateCompletion_Division(subjectId, subject.getCompletion_division());
    }

    @PutMapping("/{subjectId}/credit")
    public void updateCredit(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateCredit(subjectId, subject.getCredit());
    }

    @PutMapping("/{subjectId}/mark")
    public void updateMark(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateMark(subjectId, subject.getMark());
    }

    @PutMapping("/{subjectId}/ln")
    public void updateLecture_Num(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateLecture_Num(subjectId, subject.getLecture_num());
    }

    @PutMapping("/{subjectId}/lt")
    public void updateLecture_Time(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateLecture_Time(subjectId, subject.getLecture_time());
    }

    @PutMapping("/{subjectId}/repetition")
    public void updateRepetition(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateRepetition(subjectId, subject.getRepetition());
    }

    @PutMapping("/{subjectId}/student")
    public void updateStudent(@PathVariable Long subjectId, @RequestBody Subject subject) {
        subjectService.updateStudent(subjectId, subject.getUser_id());
    }

    @DeleteMapping("/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
