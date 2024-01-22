package com.example.studyroom.repository;

import com.example.studyroom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsBySchoolId(int schoolId);
}
