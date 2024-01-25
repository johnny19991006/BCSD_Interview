package Service;

import Domain.Student;
import Dto.StudentDTO;
import Repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

// 필요한 기능, 유저 추가, 유저 수정, 유저 삭제, 유저 업데이트
@Service
@Transactional // 클래스 전체 정의, 메소드 개별 정의 어떤 게 나을지?
@RequiredArgsConstructor // StudentRepository의 생성자 주입, final 혹은 @Notnull 붙은 것 포함
public class StudentService implements StudentServiceInterface{
    private final StudentRepository studentRepository;

    // 엔티티를 DTO로 변환하기, 이를 이용하면 필드값 변환 없이 복사 가능, 영향 x
    // 필요 유무 따져보고, 필요 없다면 삭제할 것
    private StudentDTO toDto(Student student)
    {
        return StudentDTO.builder()
                .studentId(student.getStudentId())
                .studentPw(student.getStudentPw())
                .studentName(student.getStudentName())
                .studentMajor(student.getStudentMajor())
                .studentGrade(student.getStudentGrade())
                .studentSemester(student.getStudentSemester())
                .studentAttend(student.getStudentAttend())
                .updateAt(student.getUpdateAt())
                .build();
    }

    @Override // 학생 정보 생성
    public StudentDTO createStudent(StudentDTO studentDTO)
    {
        studentRepository.save(studentDTO.toEntity());
        return studentDTO;
    }

    @Override
    public StudentDTO findById(String studentId)
    {
        return studentRepository.findById(studentId)
                .map(student -> new StudentDTO(student))
                .orElseThrow(() -> new NoSuchElementException("Can't Find."));
    }
    // Map은 Optional에서, 타입이 일치하지 않으므로 Student-> StudentDTO로 변경이 필요함
    // 없는 경우에 Can't Find를 반환하도록 했음

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO, String studentId)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Can't Find."));

        student.setStudentPw(studentDTO.getStudentPw());
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentMajor(studentDTO.getStudentMajor());
        student.setStudentGrade(studentDTO.getStudentGrade());
        student.setStudentSemester(studentDTO.getStudentSemester());
        student.setStudentAttend(studentDTO.getStudentAttend());
        student.setUpdateAt(studentDTO.getUpdateAt());

        studentRepository.save(student); // Id를 제외한 정보는 수정하여 저장 가능

        return new StudentDTO(student);
    }
    // StudentId는 고유 값을 사용하므로 수정 불가하도록 했음

    @Override
    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }
}
