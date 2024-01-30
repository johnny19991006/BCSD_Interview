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

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @Nullable
    @JsonManagedReference
    private Seat seat;*/


    /*public User(Integer schoolId){
        this.schoolId = schoolId;
    }*/

    @Builder
    public User(Integer schoolId, String name, String password){
        this.schoolId = schoolId;
        this.name = name;
        this.password = password;
        //this.seat = seat;
    }

    /*public void useSeat(Seat seat){
        this.seat = seat;
    }

    public void endSeat(){
        this.seat = null;
    }*/
}
