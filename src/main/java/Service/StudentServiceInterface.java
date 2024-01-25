package Service;

import Domain.Student;
import Dto.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {
    public StudentDTO createStudent(StudentDTO studentDTO);
    public List<Student> findAllStudent();
    public StudentDTO findById(String studentId);
    public StudentDTO updateStudent(StudentDTO studentDTO, String studentId);
    public void deleteStudent(String studentId);

}
