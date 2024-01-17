package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "User")
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private Integer user_id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 40, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, unique = true)
    private String email;
}

