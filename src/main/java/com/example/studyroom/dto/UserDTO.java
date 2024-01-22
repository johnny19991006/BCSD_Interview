package com.example.studyroom.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private int schoolId;
    private String name;
    private String password;
}
