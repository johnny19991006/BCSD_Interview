package Controller;

import Domain.Student;
import Dto.StudentDTO;
import Service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // service 생성자 생성
@RequestMapping("/Student")
public class StudentController {
    @Autowired // 필드 주입 비권장
    private final StudentService service;

    @PostMapping("")
    public StudentDTO createStudent(@RequestBody StudentDTO student)
    {
        return service.createStudent(student);
    }
    // JSON 처리 시는 @RequestBody, 일반적인 get, post에는 사용하지 않아도 된다고 함

    @GetMapping("")
    public List<Student> findAllStudent()
    {
        return service.findAllStudent();
    }
    @GetMapping("/{studentId}")
    public StudentDTO findById(@PathVariable String studentId)
    {
        return service.findById(studentId);
    }
    // 특정 정보를 가져오는 것은 @PathVariable이 적합, get 요청

    @PutMapping("/{studentId}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String studentId)
    {
        return service.updateStudent(studentDTO, studentId);
    }
    // 유저의 정보를 가져와서 수정하는 것이므로 각 @RequestBody, @PathVariable 사용했음

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable String studentId)
    {
        service.deleteStudent(studentId);
    }
}
