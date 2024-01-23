package HSAnimal.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder        // SQL 파라미터에 값을 쉽게 넣어주기 위함
@Entity(name = "keywords")  // 해당 class에서 사용할 테이블 명
@AllArgsConstructor // 매개변수에 대한 생성자들을 자동 생성
@NoArgsConstructor  // 기본생성자를 자동 생성
@Getter             // get 메소드를 자동 생성
@Setter             // set 메소드를 자동 생성
@ToString           // toString 메소드 자동 생성
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pref_id", nullable = false)
    private long prefId;

    @Column(name = "pref_name", nullable = true, length = 255)
    private String prefName; //유저이름
}