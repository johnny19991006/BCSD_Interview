package BCSD.MusicStream.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer like_id;
    Integer user_id;
    Integer music_id;
    boolean is_like;
    @Builder
    Like(Integer like_id, Integer user_id, Integer music_id, boolean is_like) {
        this.like_id = like_id;
        this.user_id = user_id;
        this.music_id = music_id;
        this.is_like = is_like;
    }

}
