package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;

import java.util.List;

public interface StudentGradeInformationServiceInterface {
    public StudentGradeInformation createStudentGradeInformation(StudentGradeInformation studentGradeInformation);

    public List<StudentGradeInformation> findAllGradeInformation();
    public StudentGradeInformation findStudentGradeInformation(String StudentId);
    public StudentGradeInformation updateStudentGradeInformation(StudentGradeInformation studentGradeInformation, String StudentId);
    public void deleteStudentGradeInformation(String studentId);
    public void updateStudentGradeInformation(String studentId); // 학생 아이디로 전체 학점 및 성적 계산 메소드
}
