package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.JwtTokenDTO;

public interface UserService {
    public JwtTokenDTO signIn(String userEmail, String password);
}
