package BCSD.MusicStream.dto.music;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;

@Getter
@Builder
public class ModifyMusicDTO {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String singerName;
    private String lyrics;
    private MultipartFile imageFile;
    private MultipartFile soundFile;
}
