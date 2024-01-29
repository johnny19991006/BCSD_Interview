package io.github.imtotem.shortly.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_url")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "url_id", nullable = false)
    private Url url;

    @Column
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private boolean deletable = true;

    public void updateUrl(Url url) {
        this.url = url;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateDeletable(boolean deletable) {
        this.deletable = deletable;
    }

}
