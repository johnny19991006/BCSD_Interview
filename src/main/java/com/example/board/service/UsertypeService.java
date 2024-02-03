package com.example.board.service;

import com.example.board.domain.Usertype;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;
import java.util.List;

public interface UsertypeService {
    public Usertype insertUsertype(Usertype usertype) throws Exception;
    public List<Usertype> getAllUsertypes();
    public void deleteUsertype(Integer usertypeId) throws EmptyResultDataAccessException;
}
