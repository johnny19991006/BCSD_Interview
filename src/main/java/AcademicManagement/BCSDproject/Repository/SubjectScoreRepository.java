package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.SubjectScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectScoreRepository extends JpaRepository<SubjectScore, String> {
    List<SubjectScore> findByStudentId(String studentId);
}
