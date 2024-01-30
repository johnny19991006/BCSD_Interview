package com.example.board.controller;

import com.example.board.domain.User;
import com.example.board.domain.Usertype;
import com.example.board.service.UsertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
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
    public Usertype insertUsertype(@RequestBody Usertype usertype) throws SQLException {
        return usertypeService.insertUsertype(usertype);
    }
    @GetMapping
    public List<Usertype> getAllUsertypes() throws SQLException {
        return usertypeService.getAllUsertypes();
    }
    @DeleteMapping("/{usertypeId}")
    public void deleteUsertype(@PathVariable Integer usertypeId) throws SQLException {
        usertypeService.deleteUsertype(usertypeId);
    }
}
