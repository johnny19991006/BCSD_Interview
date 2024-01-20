package bcsd.backend.project.pokku.dto.UserInfoSNS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoSNSRequest {
    private String userId;
    private String userGithub;
    private String userBlog;
    private String userInstagram;
}
