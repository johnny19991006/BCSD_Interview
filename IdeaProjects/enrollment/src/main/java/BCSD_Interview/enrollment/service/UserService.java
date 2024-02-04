package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.DTO.SignUpDTO;
import BCSD_Interview.enrollment.DTO.UserDTO;
import BCSD_Interview.enrollment.domain.JwtToken;
import BCSD_Interview.enrollment.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    UserDTO signUp(SignUpDTO signUpDTO);

    @Transactional
    JwtToken signIn(String username, String password);

    User insertUser(User user);
    List<User> getAllUsers();
    User getUserByUserId(Long userId);
    void updateUsername(Long userId, String username);
    void updatePassword(Long userId, String password);
    void updateDepartment(Long userId, String department);
    void updateGrade(Long userId, Integer grade);
    void updateRegistrable_Grade(Long userId, String registrable_grade);
    void deleteUser(Long userId);
}
