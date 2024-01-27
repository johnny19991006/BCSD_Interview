package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    List<Semester> findByStudentId(String studentId);
}
