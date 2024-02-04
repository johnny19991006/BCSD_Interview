package BCSD_Interview.enrollment.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id", updatable = false, unique = true, nullable = false)
    private Integer user_id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "registrable_grade", nullable = false)
    private String registrable_grade;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setRegistrable_grade(String registrable_grade) {
        this.registrable_grade = registrable_grade;
    }

    public void settingRegistrable_Grade() {
        List<String> RG = new ArrayList<>();
        switch (this.department) {
            case "기계공학부":
                this.registrable_grade = "기공";
                break;
            case "메카트로닉스공학부":
                this.registrable_grade = "메카";
                break;
            case "전기전자통신공학부":
                this.registrable_grade = "전통";
                break;
            case "컴퓨터공학부":
                this.registrable_grade = "컴부";
                break;
            case "디자인건축공학부":
                this.registrable_grade = "디건";
                break;
            case "에너지신소재화학공학부":
                this.registrable_grade = "에화";
                break;
            case "산업경영학부":
                this.registrable_grade = "산경";
                break;
            case "고용서비스정책학과":
                this.registrable_grade = "고용서비스";
                break;
            default:
                this.registrable_grade = this.department;
        }
        if (this.grade > 0 && this.grade <= 4) {
            this.registrable_grade += this.grade;
        } else if (this.grade > 4) {
            this.registrable_grade += 4;
        }
        RG.add("전체");
        RG.add(this.registrable_grade + "전체");
        for (int i = 1; i <= this.grade; i++) {
            RG.add(String.valueOf(i));
            RG.add(this.registrable_grade + 1);
        }
        this.registrable_grade += String.join(",", RG);
    }
}