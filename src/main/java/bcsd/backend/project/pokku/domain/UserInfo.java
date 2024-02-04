package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
@Getter
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
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private PortfolioAbout portfolioAbout;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private UserInfoGithub userInfoGithub;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private UserInfoInstagram userInfoInstagram;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private UserInfoBlog userInfoBlog;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsFrontend> userPortfolioSkillsFrontendList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsBackend> userPortfolioSkillsBackendList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsDeployment> userPortfolioSkillsDeploymentList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsMobileapp> userPortfolioSkillsMobileappsList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsVersioncontrol> userPortfolioSkillsVersioncontrolsList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsCommunication> userPortfolioSkillsCommunicationList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsCertification> userPortfolioSkillsCertifications = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<PortfolioArchiving> portfolioArchivingList = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.authorities = role;
        role.forEach(o -> o.setUsers(this));
    }

    @Builder
    public UserInfo(String userBirth, String userEducation, String userEmail, String userId, String userName, String userNickname, String userPassword, String userTel){
        this.userBirth = userBirth;
        this.userEducation = userEducation;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userTel = userTel;
    }

}
