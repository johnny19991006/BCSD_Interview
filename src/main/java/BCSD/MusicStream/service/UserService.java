package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.JwtTokenDTO;
import BCSD.MusicStream.dto.SignUpDTO;
import BCSD.MusicStream.dto.UserDTO;

public interface UserService {
    public JwtTokenDTO signIn(String userEmail, String password);
    public void signUp(SignUpDTO signUpDTO);
    public Boolean existsByUserEmail(String userEmail);
    public void deleteUserByUserEmail(String userEmail);

}
