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
public class User implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "role_name")
    private List<String> roles = new ArrayList<>();

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = ALL, orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @JsonIgnore
    // ToDo (fetch = FetchType.EAGER) 성능에 문제가 발생할 수 있을 것 같은데 어떻게 처리 할지 고민
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = ALL, orphanRemoval = true)
    private List<Post> scrapList = new ArrayList<>();

    public void addPost(Post post){
        postList.add(post);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void addScrap(Post post) {scrapList.add(post);}
}


