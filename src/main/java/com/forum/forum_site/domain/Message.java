package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Message")
@NoArgsConstructor
@Getter
@Entity
public class Message {
    @Id @GeneratedValue
    private Integer message_id;

    @Column(nullable = false)
    private Integer sender_id;

    @Column(nullable = false)
    private Integer receiver_id;

    @Lob
    @Column(nullable = false)
    private String content;
}
