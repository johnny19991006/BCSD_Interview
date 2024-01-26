package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findBySubjectName(String subjectName); // findById가 호출이 안되서 추가
}
