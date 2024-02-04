package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.Subject;

import java.util.List;

public interface SubjectService {
    Subject insertSubject(Subject subject);
    List<Subject> getAllSubject_Record();
    Subject getSubjectBySubjectId(Long subjectId);
    void updateAcademic_Year(Long subjectId, Integer academic_year);
    void updateSemester(Long subjectId, String semester);
    void updateGrade(Long subjectId, Integer grade);
    void updateSubject_Code(Long subjectId, String subject_code);
    void updateSubject_Name(Long subjectId, String subject_name);
    void updateClass_Num(Long subjectId, Integer class_num);
    void updateProfessor(Long subjectId, String professor);
    void updateCompletion_Division(Long subjectId, String completion_division);
    void updateCredit(Long subjectId, Integer credit);
    void updateMark(Long subjectId, String mark);
    void updateLecture_Num(Long subjectId, Integer lecture_num);
    void updateLecture_Time(Long subjectId, String lecture_time);
    void updateRepetition(Long subjectId, String repetition);
    void updateStudent(Long subjectId, Long user_id);
    void deleteSubject(Long subjectId);
}
