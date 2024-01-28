package io.github.imtotem.shortly.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "short_url")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Url {
    @Id
    @SequenceGenerator(name = "seq_generator", sequenceName = "url_seq", initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    private Long id;

    @Setter
    @Column(name = "short_url")
    private String shortUrl;

    @Lob
    @Column(name = "origin_url", nullable = false, updatable = false)
    private String originUrl;

    @Builder.Default
    @Column(nullable = false)
    private int cnt = 0;

    public void increase() {
        cnt++;
    }

    public void decrease() {
        cnt--;
    }
}
