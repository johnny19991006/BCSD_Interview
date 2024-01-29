package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "user_email", unique = true, length = 20)
    private String userEmail;

    @Column(name = "user_pw", length = 20)
    private String userPw;

    @Column(name = "user_nickname", unique = true, length = 20)
    private String userNickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "usertype_id")
    private Usertype userType;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> boards;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> comments;
}
