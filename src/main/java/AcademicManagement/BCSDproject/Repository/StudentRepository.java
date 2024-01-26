package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String>{
}
