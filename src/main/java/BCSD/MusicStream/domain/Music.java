package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Column(name = "singer_name", nullable = false, length = 10)
    private String singerName;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "image_file_name")
    private String imageFileName;

    @Column(name = "sound_file_name")
    private String soundFileName;
}
