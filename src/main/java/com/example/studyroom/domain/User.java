package com.example.studyroom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "school_id")
    private int schoolId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private Seat seat;

    public User() {
    }
}
