package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequestDTO {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private int userTypeId;
}
