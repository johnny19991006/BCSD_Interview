package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Scrap")
@Data
@NoArgsConstructor
@Entity
public class Scrap {
    @Id @GeneratedValue
    private Integer scrap_id;

    @Column(nullable = false)
    private Integer post_id;

    @Column(nullable = false)
    private Integer user_id;
}
