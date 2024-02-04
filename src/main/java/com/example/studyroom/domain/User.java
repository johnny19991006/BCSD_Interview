package com.example.studyroom.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
public class User {
    public User() {
    }

    @Id
    @Column(name = "school_id")
    private Integer schoolId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String password;

    @Builder
    public User(Integer schoolId, String name, String password) {
        this.schoolId = schoolId;
        this.name = name;
        this.password = password;
        //this.seat = seat;
    }
}
