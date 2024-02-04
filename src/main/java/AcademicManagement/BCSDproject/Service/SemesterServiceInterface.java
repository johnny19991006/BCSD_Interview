package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Enum.SemesterEnum;
import AcademicManagement.BCSDproject.Enum.SemesterGradeEnum;

import java.util.List;

public interface SemesterServiceInterface {
    public Semester createSemester(Semester semester);
    public List<Semester> findAllSemester();
    public Semester updateSemester(Semester semester, int semesterId);
    public void deleteSemester(int semesterId);
    public List<Semester> studentSemester(String studentId);
    public void updateSemesterCredit(String studentId, SemesterGradeEnum semesterGradeEnum,
                                     SemesterEnum semesterEnum); // 학기와 학년을 이용하여 성적 계산하는 메소드
}
