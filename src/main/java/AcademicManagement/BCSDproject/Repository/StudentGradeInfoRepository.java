package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGradeInfoRepository extends JpaRepository<StudentGradeInformation, String> {
}
