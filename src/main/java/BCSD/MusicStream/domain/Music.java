package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "weather_id", referencedColumnName = "id")
    private Weather weather;

    @Column(name = "singer_name", nullable = false, length = 10)
    private String singerName;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "image_file_name")
    private String imageFileName;

    @Column(name = "sound_file_name")
    private String soundFileName;

    public void setFilesName(String soundFileName, String imageFileName) {
        Objects.requireNonNull(soundFileName);
        Objects.requireNonNull(imageFileName);
        this.soundFileName = soundFileName;
        this.imageFileName = imageFileName;
    }
}
