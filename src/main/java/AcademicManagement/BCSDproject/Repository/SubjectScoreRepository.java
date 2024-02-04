package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.SubjectScore;
import AcademicManagement.BCSDproject.Enum.SemesterEnum;
import AcademicManagement.BCSDproject.Enum.SemesterGradeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectScoreRepository extends JpaRepository<SubjectScore, String> {
    List<SubjectScore> findByStudentId(String studentId);
    Optional<SubjectScore> findBySubjectNameAndStudentId(String subjectName, String studentId);
    // Optional을 사용해야 orElseThrow 사용가능
    List<SubjectScore> findByStudentIdAndSemesterGradeEnumAndSemesterEnum(String studentId,
                                                          SemesterGradeEnum semesterGradeEnum,
                                                          SemesterEnum semesterEnum);
}
