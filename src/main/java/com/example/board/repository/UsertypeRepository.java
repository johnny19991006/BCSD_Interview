package com.example.board.repository;

import com.example.board.domain.Usertype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsertypeRepository extends JpaRepository<Usertype, Integer> {
    List<Usertype> findAllByOrderByUserTypeIdAsc();
}
