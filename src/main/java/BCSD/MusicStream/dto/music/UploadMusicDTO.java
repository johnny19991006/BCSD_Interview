package BCSD.MusicStream.dto.music;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;

@Getter
@Builder
public class UploadMusicDTO {
    private String name;
    private Integer categoryId;
    private String singerName;
    private String lyrics;
    private Time duration;
    private MultipartFile imageFile;
    private MultipartFile soundFile;
}
