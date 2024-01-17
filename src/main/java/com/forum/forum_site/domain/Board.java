package com.forum.forum_site.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Board")
@Data
@NoArgsConstructor
@Entity
public class Board {
    @Id @GeneratedValue
    private Integer board_id;

    @Column(length = 255, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column
    private Integer creator_id;
}
