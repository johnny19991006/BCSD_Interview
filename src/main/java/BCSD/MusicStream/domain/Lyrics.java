package BCSD.MusicStream.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Lyrics {
    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;
}
