package Repository;

import Domain.StudentGradeInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGradeInfoRepository extends JpaRepository<StudentGradeInformation, String> {
}
