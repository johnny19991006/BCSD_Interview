package BCSD.MusicStream.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Iyrics {
    @Id
    Integer music_id;
    String iyrics_contents;
    @Builder
    Iyrics(Integer music_id, String iyrics_contents) {
        this.music_id = music_id;
        this.iyrics_contents = iyrics_contents;
    }
}
