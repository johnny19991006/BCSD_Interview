package BCSD.MusicStream.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer category_id;
    String category_name;
    @Builder
    Category(Integer category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }
}
