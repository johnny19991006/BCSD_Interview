package com.example.board.dto;

import com.example.board.domain.Usertype;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserResponseSimpleDTO {
    private int userId;
    private String userName;
    private String userNickname;
    private LocalDateTime createdAt;
    private Usertype userType;
}
