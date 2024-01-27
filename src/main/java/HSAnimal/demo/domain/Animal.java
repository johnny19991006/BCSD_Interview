package HSAnimal.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder        // SQL 파라미터에 값을 쉽게 넣어주기 위함
@Entity(name = "animals")  // 해당 class에서 사용할 테이블 명
@AllArgsConstructor // 매개변수에 대한 생성자들을 자동 생성
@NoArgsConstructor  // 기본생성자를 자동 생성
@Getter             // get 메소드를 자동 생성
@Setter             // set 메소드를 자동 생성
@ToString           // toString 메소드 자동 생성
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false)
    private long animalId;

    @Column(name = "animal_name", nullable = false, length = 255)
    private String animalName; //유저이름

    @Column(name = "animal_pref", nullable = false, length = 255)
    private String animalPref; //비밀번호
}
