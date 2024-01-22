package com.example.board.service;

import com.example.board.domain.Usertype;

import java.sql.SQLException;
import java.util.List;

public interface UsertypeService {
    public Usertype insertUsertype(Usertype usertype) throws SQLException;
    public List<Usertype> getAllUsertypes() throws SQLException;
    public void deleteUsertype(Integer usertypeId) throws SQLException;
}
