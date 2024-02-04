package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomStudentDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException
    {
        return studentRepository.findByStudentId(studentId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find User"));
    }

    private UserDetails createUserDetails(Student student)
    {
        return Student.builder()
                .studentId(student.getUsername())
                .studentPw(passwordEncoder.encode(student.getPassword()))
                .roles(student.getRoles())
                .build();
    }
}
