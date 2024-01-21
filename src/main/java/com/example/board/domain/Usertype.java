package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usertypes")
public class Usertype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usertype_id")
    private int userTypeId;

    @Column(name = "type_name", unique = true, length = 20)
    private String typeName;

    @OneToMany(mappedBy = "userType")
    private List<User> users;
}
