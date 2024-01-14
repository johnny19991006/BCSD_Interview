package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.SignUpRequest;
import bcsd.backend.project.pokku.dto.UserRequest;
import bcsd.backend.project.pokku.dto.UserResponse;
import bcsd.backend.project.pokku.service.SignUpServiceImpl;
import bcsd.backend.project.pokku.service.UserServiceImpl;
import com.xkcoding.http.support.Http;
import org.apache.catalina.User;
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

    @PostMapping(value = "/")
    public ResponseEntity<UserResponse> findUser(@RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.findUsers(request), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Boolean> updateUser(@RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.UpdateUsers(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.DeleteUsers(request), HttpStatus.OK);
    }
}
