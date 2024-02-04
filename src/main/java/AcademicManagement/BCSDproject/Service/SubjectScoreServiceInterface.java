package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.SubjectScore;

import java.util.List;

public interface SubjectScoreServiceInterface {
    public SubjectScore createSubjectScore(SubjectScore subjectScore, String subjectName);
    public List<SubjectScore> findAllSubjectScore();
    public SubjectScore findSubjectScore(String subjectName, String studentId);
    public SubjectScore updateSubjectScore(SubjectScore subjectScore, String subjectName, String studentId);
    public void deleteSubjectScore(String subjectName, String studentId);
    public List<SubjectScore> findStudentScoreByStudentId(String studentId);

}
