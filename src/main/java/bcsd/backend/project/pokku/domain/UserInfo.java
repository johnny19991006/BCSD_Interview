package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_education")
    private String userEducation;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Authority> authorities = new ArrayList();

    public void setRoles(List<Authority> role) {
        this.authorities = role;
        role.forEach(o -> o.setUsers(this));
    }

}
