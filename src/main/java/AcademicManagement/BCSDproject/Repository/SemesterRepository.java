package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
