package com.example.studyroom.repository;

import com.example.studyroom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findBySchoolId(int schoolId);
}
