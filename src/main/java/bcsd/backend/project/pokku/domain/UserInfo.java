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
    private String UserId;

    @Column(name = "user_name")
    private String UserName;

    @Column(name = "user_birth")
    private String UserBirth;

    @Column(name = "user_tel")
    private String UserTel;

    @Column(name = "user_email")
    private String UserEmail;

    @Column(name = "user_password")
    private String UserPassword;

    @Column(name = "user_nickname")
    private String UserNickname;

    @Column(name = "user_education")
    private String UserEducation;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> authorities = new ArrayList();

    public void setRoles(List<Authority> role) {
        this.authorities = role;
        role.forEach(o -> o.setUsers(this));
    }

}
