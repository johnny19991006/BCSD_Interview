package com.example.board.dto;

import com.example.board.domain.Usertype;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserResponseDTO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userNickname;
    private LocalDateTime createdAt;
    private Usertype userType;
}
