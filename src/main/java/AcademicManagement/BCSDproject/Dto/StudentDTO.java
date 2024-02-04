package AcademicManagement.BCSDproject.Dto;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/*
Student나 StudentGradeInformation 같은 정보는 DTO로 전달하는 것이 좋을 것이라 생각했습니다
*/
@Getter
@NoArgsConstructor
public class StudentDTO {
    private String studentId;
    private String studentPw;
    private String studentName;
    private String studentMajor;
    private Integer studentGrade;
    private Integer studentSemester;
    private String studentAttend;
    private List<String> roles;
    private LocalDateTime updateAt;

    public Student toEntity()
    {
        Student student = Student.builder()
                .studentId(studentId)
                .studentPw(studentPw)
                .studentName(studentName)
                .studentMajor(studentMajor)
                .studentGrade(studentGrade)
                .studentSemester(studentSemester)
                .studentAttend(studentAttend)
                .updateAt(updateAt)
                .roles(roles)
                .build();
        return student;
    }


    @Builder
    public StudentDTO(String studentId, String studentPw, String studentName,
                      String studentMajor, Integer studentGrade, Integer studentSemester,
                      String studentAttend, LocalDateTime updateAt, List<String> roles)
    {
        this.studentId = studentId;
        this.studentPw = studentPw;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentGrade = studentGrade;
        this.studentSemester = studentSemester;
        this.studentAttend = studentAttend;
        this.updateAt = updateAt;
        this.roles = roles;
    }

    // findById에서 사용할 studentDTO의 student 생성자
    // 사용하지 않으면 map 사용 시 오류 발생 가능성 있음
    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.studentPw = student.getStudentPw();
        this.studentName = student.getStudentName();
        this.studentMajor = student.getStudentMajor();
        this.studentGrade = student.getStudentGrade();
        this.studentSemester = student.getStudentSemester();
        this.studentAttend = student.getStudentAttend();
        this.updateAt = student.getUpdateAt();
        this.roles = student.getRoles();
    }
}
