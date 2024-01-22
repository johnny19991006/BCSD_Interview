package com.example.board.dto;

import com.example.board.domain.Usertype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String userNickname;
    private LocalDateTime createdAt;
    private Usertype userType;
}