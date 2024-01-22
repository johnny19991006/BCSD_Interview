package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Member user;

    @ManyToOne
    @JoinColumn(name = "music_id", referencedColumnName = "id")
    private Music music;

    @Column(name = "is_like")
    private Boolean isLike;
}
