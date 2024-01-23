package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "Comment")
@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "comment_id")
    private Comment parent;

    @Lob
    @Column // (nullable = false)
    private String content;

    @Column
    private Integer likes_count = 0;

    private boolean is_Removed= false;

    // 댓글 삭제 되어도 대댓글은 남음
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();

    public void confirmAuthor(User user) {
        this.author = user;
        user.addComment(this);
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    public void confirmParent(Comment parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child) {
        childList.add(child);
    }

    // 수정
    public void updateContent(String content) {
        this.content = content;
    }

    // 삭제
    public void remove() {
        is_Removed = true;
    }

    @Builder
    public Comment( User author, Post post, Comment parent, String content) {
        this.author = author;
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.is_Removed = false;
    }

    public List<Comment> findRemovableList() {
        List<Comment> result = new ArrayList<>();

        Optional.ofNullable(this.parent).ifPresentOrElse(

                // 대댓글인 경우
                parentComment ->{
                    if( parentComment.is_Removed() && parentComment.isAllChildRemoved()) {
                        result.addAll(parentComment.getChildList());
                        result.add(parentComment);
                    }
                },

                // 댓글인 경우
                () -> {
                    if (isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildList());
                    }
                }
        );
        return result;
    }

    // 모든 대댓글이 삭제 되었는지를 확인
    private boolean isAllChildRemoved() {
        return getChildList().stream()
                .map(Comment::is_Removed)
                .filter(is_Removed -> !is_Removed)
                .findAny()
                .orElse(true);
    }
}
