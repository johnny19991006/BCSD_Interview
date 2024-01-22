package BCSD.MusicStream.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Lyric {
    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;
}
