package BCSD.MusicStream.dto.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class MusicInfoDTO {
    private Integer id;
    private Integer categoryId;
    private Integer weatherId;
    private String name;
    private String singerName;
    private String duration;
    private String lyrics;
    private String imageFilePath;
    private String soundFilePath;
}
