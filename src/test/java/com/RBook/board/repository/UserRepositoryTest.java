package com.RBook.board.repository;

import com.RBook.board.domain.Gender;
import com.RBook.board.domain.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void saveUser() {
        User saveParams = User.builder()
                .loginId("abc123")
                .loginPw("1234")
                .userName("함지애")
                .nickname("당근맛연어")
                .gender(Gender.F)
                .userEmail("coup31307@gmail.com")
                .birthday(LocalDate.of(2003, 10 ,8))
                .build();

        User user = userRepository.save(saveParams);
        Assertions.assertEquals(user.getLoginId(), "abc123");
    }

    @Test
    void findAllUser() {
        userRepository.findAll();
    }

    @Test
    void findUserById() {
        User user = userRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(user.getLoginId(), "abc123");
    }

    @Test
    void deleteUserById() {
        userRepository.deleteById(2L);
    }

}