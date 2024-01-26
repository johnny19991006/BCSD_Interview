package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Subject;

import java.util.List;

// 1차적으로 과목에 대한 정보들을 이 DB에 저장, 추가적으로 성적을 불러 올 수 있도록 하는 로직 구현
public interface SubjectServiceInterface {
    public Subject createSubject(Subject subject);
    public List<Subject> findAllSubject();
    public Subject findById(String subjectName);
    public Subject updateSubject(Subject subject, String subjectName);
    public void deleteSubject(String subjectName);
}