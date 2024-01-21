package com.RBook.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User")
public class User extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", length = 20, nullable = false)
    private String loginId;

    @Column(name = "login_pw", length = 30, nullable = false)
    private String loginPw;

    @Column(name = "user_name", length = 10, nullable = false)
    private String userName;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "user_email", length = 100, nullable = false)
    private String userEmail;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @Builder
    public User(Long id, String loginId, String loginPw, String userName, String nickname, String userEmail, Gender gender, LocalDate birthday) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userName = userName;
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.gender = gender;
        this.birthday = birthday;

    }

}
