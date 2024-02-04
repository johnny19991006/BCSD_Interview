package com.example.board.service;

import com.example.board.domain.Usertype;
import com.example.board.repository.UsertypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsertypeServiceImpl implements UsertypeService{
    private final UsertypeRepository usertypeRepository;
    @Autowired
    public UsertypeServiceImpl(UsertypeRepository repository) {
        this.usertypeRepository = repository;
    }
    @Override
    public Usertype insertUsertype(Usertype usertype) { // 유저타입 추가
        return usertypeRepository.save(usertype);
    }
    @Override
    public List<Usertype> getAllUsertypes() { // 유저타입 전체조회 (id 오름차순)
        return usertypeRepository.findAllByOrderByUserTypeIdAsc();
    }
    @Override
    public void deleteUsertype(Integer usertypeId) throws EmptyResultDataAccessException {
        usertypeRepository.deleteById(usertypeId);
    }
}
