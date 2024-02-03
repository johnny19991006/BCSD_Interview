    package AcademicManagement.BCSDproject.Controller;

    import AcademicManagement.BCSDproject.Domain.Student;
    import AcademicManagement.BCSDproject.Domain.TokenInfo;
    import AcademicManagement.BCSDproject.Dto.SignInDTO;
    import AcademicManagement.BCSDproject.Dto.StudentDTO;
    import AcademicManagement.BCSDproject.Service.StudentService;

    import org.h2.command.Token;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/Student")
    public class StudentController {
        private final StudentService service;

        @Autowired
        public StudentController(StudentService service) {
            this.service = service;
        }

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

        @PreAuthorize("hasRole('USER')")
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

        @PostMapping("/{signin}")
        public TokenInfo signIn(@RequestBody SignInDTO signInDTO)
        {
            String studentId = signInDTO.getStudentId();
            String studentPw = signInDTO.getStudentPw();
            TokenInfo tokenInfo = service.signIn(studentId, studentPw);
            return tokenInfo;
        }
    }
