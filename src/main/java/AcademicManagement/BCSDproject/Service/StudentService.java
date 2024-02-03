package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Domain.TokenInfo;
import AcademicManagement.BCSDproject.Dto.StudentDTO;
import AcademicManagement.BCSDproject.Repository.StudentRepository;

import AcademicManagement.BCSDproject.Security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.List;

// 필요한 기능, 유저 추가, 유저 수정, 유저 삭제, 유저 업데이트
@Service
@Transactional
@RequiredArgsConstructor // StudentRepository의 생성자 주입, final 혹은 @Notnull 붙은 것 포함
public class StudentService implements StudentServiceInterface{
    private final StudentRepository studentRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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
                .roles(student.getRoles())
                .build();
    }

    @Override // 학생 정보 생성
    public StudentDTO createStudent(StudentDTO studentDTO)
    {
        Student student = new Student();
        student.setRoles(Collections.singletonList("USER"));
        studentRepository.save(studentDTO.toEntity());
        return studentDTO;
    }

    @Override
    public List<Student> findAllStudent()
    {
        return studentRepository.findAll();
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

        studentRepository.save(student); // Id를 제외한 정보는 수정하여 저장 가능

        return new StudentDTO(student);
    }
    // StudentId는 고유 값을 사용하므로 수정 불가하도록 했음

    @Override
    public void deleteStudent(String studentId)
    {
        studentRepository.deleteById(studentId);
    }

    public TokenInfo signIn(String studentId, String studentPw)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentId, studentPw);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
}
