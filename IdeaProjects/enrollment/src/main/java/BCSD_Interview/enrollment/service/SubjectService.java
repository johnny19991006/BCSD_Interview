package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.Subject;

import java.util.List;

public interface SubjectService {
    Subject insertSubject(Subject subject);
    List<Subject> getAllSubject_Record();
    Subject getSubjectBySubjectId(Long subjectId);
    void deleteSubject(Long subjectId);
}
