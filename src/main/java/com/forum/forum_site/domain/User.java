package com.forum.forum_site.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.ALL;

@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder

// UserDetails
// 로그인/ 로그아웃 관리 및 secured resource에 대한 접근 관리를 spring security가 처리 해줌

public class User implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    // 권한 반환
    // Collection<? extends GrantedAuthority>는  GrantedAuthority 또는 그 하위 타입의 알 수 없는 타입을 의미
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return authorities;
    }


    // 사용자 이름 반환
    @Override
    public String getUsername() {
        return username;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 회원 탈퇴시 작성한 게시물 및 댓글, 스크랩 삭제
    @JsonIgnore
    @Builder.Default // Builder.Defalt를 사용시 객체 생성 할때 리스트가 null이 아닌 빈 리스트로 초기화 됨
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    // ToDo (fetch = FetchType.EAGER) 성능에 문제가 발생할 수 있을 것 같은데 어떻게 처리 할지 고민
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", orphanRemoval = true)
    private List<Post> scrapList = new ArrayList<>();

    public void addPost(Post post){
        postList.add(post);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void addScrap(Post post) {scrapList.add(post);}
}


