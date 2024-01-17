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
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer authority_id;
    String authority_type;
    @Builder
    Authority(Integer authority_id, String authority_type) {
        this.authority_id = authority_id;
        this.authority_type = authority_type;
    }
}
