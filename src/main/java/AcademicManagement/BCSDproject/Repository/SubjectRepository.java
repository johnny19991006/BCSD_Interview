package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
