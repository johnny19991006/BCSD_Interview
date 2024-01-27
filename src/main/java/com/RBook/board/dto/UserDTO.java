package com.RBook.board.dto;

import com.RBook.board.domain.Gender;
import com.RBook.board.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String loginID;
    private String loginPw;
    private String userName;
    private String nickname;
    private String userEmail;
    private Gender gender;
    private LocalDate birthday;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .loginId(loginID)
                .loginPw(loginPw)
                .userName(userName)
                .nickname(nickname)
                .userEmail(userEmail)
                .gender(gender)
                .birthday(birthday)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long id, String loginID, String loginPw,
                   String userName, String nickname, String userEmail,
                   Gender gender, LocalDate birthday, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.id = id;
        this.loginID = loginID;
        this.loginPw = loginPw;
        this.userName = userName;
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.gender = gender;
        this.birthday = birthday;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }


}
