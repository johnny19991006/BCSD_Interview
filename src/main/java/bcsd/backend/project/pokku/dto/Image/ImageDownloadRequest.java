package bcsd.backend.project.pokku.dto.Image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageDownloadRequest {
    private List<String> imageName;
}
