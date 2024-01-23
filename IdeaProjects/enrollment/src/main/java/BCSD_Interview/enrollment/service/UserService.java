package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.DTO.SignUpDTO;
import BCSD_Interview.enrollment.DTO.UserDTO;
import BCSD_Interview.enrollment.domain.JwtToken;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    UserDTO signUp(SignUpDTO signUpDTO);

    @Transactional
    JwtToken signIn(String username, String password);
}
