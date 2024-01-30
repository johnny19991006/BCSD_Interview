package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "usertype_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userTypeId;

    @Column(name = "type_name", unique = true, length = 20)
    private String typeName;

    @OneToMany(mappedBy = "userType")
    @JsonIgnore
    private List<User> users;
}
