package com.example.board.controller;

import com.example.board.domain.Usertype;
import com.example.board.service.UsertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usertypes")
public class UsertypeController {
    private final UsertypeService usertypeService;
    @Autowired
    public UsertypeController(UsertypeService usertypeService) {
        this.usertypeService = usertypeService;
    }
    @PostMapping
    public ResponseEntity<Usertype> insertUsertype(@RequestBody Usertype usertype) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usertypeService.insertUsertype(usertype));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Usertype>> getAllUsertypes() {
        return ResponseEntity.ok().body(usertypeService.getAllUsertypes());
    }
    @DeleteMapping("/{usertypeId}")
    public ResponseEntity<Void> deleteUsertype(@PathVariable Integer usertypeId) {
        try {
            usertypeService.deleteUsertype(usertypeId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
