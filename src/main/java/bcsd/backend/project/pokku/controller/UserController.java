package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findUser(@PathVariable String userId) throws Exception{

        return new ResponseEntity<>(userService.findUsers(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable String userId, @RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.UpdateUsers(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) throws Exception{

        return new ResponseEntity<>(userService.DeleteUsers(userId), HttpStatus.OK);
    }
}
