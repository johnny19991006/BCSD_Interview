package AcademicManagement.BCSDproject.Repository;

import AcademicManagement.BCSDproject.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String>{
    Optional<Student> findByStudentId(String studentId); // 로그인 시 사용하려고 추가함
}
