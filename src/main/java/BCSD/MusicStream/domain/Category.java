package BCSD.MusicStream.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of = "user_id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Integer categoryId;
    @Column(name = "category_name")
    String categoryName;
}
