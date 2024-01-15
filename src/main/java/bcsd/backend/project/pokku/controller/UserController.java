package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.UserDFRequest;
import bcsd.backend.project.pokku.dto.UserRequest;
import bcsd.backend.project.pokku.dto.UserResponse;
import bcsd.backend.project.pokku.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setSignService(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> findUser(@RequestBody UserDFRequest request) throws Exception{

        return new ResponseEntity<>(userService.findUsers(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateUser(@RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.UpdateUsers(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserDFRequest request) throws Exception{

        return new ResponseEntity<>(userService.DeleteUsers(request), HttpStatus.OK);
    }
}
