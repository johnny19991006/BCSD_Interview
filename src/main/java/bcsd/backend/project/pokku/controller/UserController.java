package bcsd.backend.project.pokku.controller;

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

    @PostMapping//(value = "/{id}")
    public ResponseEntity<UserResponse> findUser(@RequestBody UserRequest request/*@RequestParam(name = "id") String id*/) throws Exception{
//        UserRequest request = new UserRequest();
//        request.setUserId(id);
        return new ResponseEntity<>(userService.findUsers(request), HttpStatus.OK);
    }

    @PutMapping//(value = "/{id}")
    public ResponseEntity<Boolean> updateUser(@RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.UpdateUsers(request), HttpStatus.OK);
    }

    @DeleteMapping//(value = "/{id}")
    public ResponseEntity<Boolean> deleteUser(@RequestBody UserRequest request/*@RequestParam(name = "id") String id*/) throws Exception{
//        UserRequest request = new UserRequest();
//        request.setUserId(id);
        return new ResponseEntity<>(userService.DeleteUsers(request), HttpStatus.OK);
    }
}
