package com.example.studyroom.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
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
}
