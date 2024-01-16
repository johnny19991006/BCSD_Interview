package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info_github")
public class UserInfoGithub {

    @Id
    @Column(name = "user_github")
    private String userGithub;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

}
