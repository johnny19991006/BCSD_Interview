package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity(name = "admin")
@NoArgsConstructor
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId;

    public Admin (int id, String userId){
        this.id = id;
        this.userId = userId;
    }
}
