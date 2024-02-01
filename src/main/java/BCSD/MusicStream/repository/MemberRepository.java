package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String memberEmail);
}
