package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer music_id;
    private String music_name;
    private Integer category_id;
    private String singer_name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
    private LocalTime music_time;
    @Builder
    Music(Integer music_id, String music_name, Integer category_id, String singer_name, LocalTime music_time) {
        this.music_id = music_id;
        this.music_name = music_name;
        this.category_id = category_id;
        this.singer_name = singer_name;
        this.music_time = music_time;
    }
}
