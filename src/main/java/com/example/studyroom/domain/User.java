package com.example.studyroom.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Table(name = "users")
@Getter
public class User {
    public User() {}


    @Id
    @Column(name = "school_id")
    private Integer schoolId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @Nullable
    private Seat seat;

    public User(Integer schoolId){
        this.schoolId = schoolId;
    }

    public User(Integer schoolId, String name, String password, Seat seat){
        this.schoolId = schoolId;
        this.name = name;
        this.password = password;
        this.seat = seat;
    }
}
