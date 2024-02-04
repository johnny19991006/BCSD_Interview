package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_info_github")
public class UserInfoGithub {

    @Id
    @Column(name = "user_github")
    private String userGithub;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @Builder
    public UserInfoGithub(String userGithub, UserInfo userInfo){
        this.userGithub = userGithub;
        this.userInfo = userInfo;
    }

}
