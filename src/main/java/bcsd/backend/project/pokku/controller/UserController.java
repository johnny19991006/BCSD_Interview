package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSRequest;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSResponse;
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
    public ResponseEntity<UserResponse> findUser(@PathVariable(name = "id") String userId) throws Exception{

        return new ResponseEntity<>(userService.findUsers(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable(name = "id") String userId, @RequestBody UserRequest request) throws Exception{
        return new ResponseEntity<>(userService.UpdateUsers(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id") String userId) throws Exception{

        return new ResponseEntity<>(userService.DeleteUsers(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/blog/{id}")
    public ResponseEntity<Boolean> addBlog(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.addBlog(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/blog/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.deleteBlog(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/blog/{id}")
    public ResponseEntity<Boolean> updateBlog(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.updateBlog(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<UserSNSResponse> findBlog(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.findBlog(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/github/{id}")
    public ResponseEntity<Boolean> addGithub(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.addGithub(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/github/{id}")
    public ResponseEntity<Boolean> deleteGithub(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.deleteGithub(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/github/{id}")
    public ResponseEntity<Boolean> updateGithub(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.updateGithub(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/github/{id}")
    public ResponseEntity<UserSNSResponse> findGithub(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.findGithub(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/instagram/{id}")
    public ResponseEntity<Boolean> addInstagram(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.addInstagram(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/instagram/{id}")
    public ResponseEntity<Boolean> deleteInstagram(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.deleteInstagram(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/instagram/{id}")
    public ResponseEntity<Boolean> updateInstagram(@PathVariable(name = "id") String userId, @RequestBody UserSNSRequest request) throws Exception{
        return new ResponseEntity<>(userService.updateInstagram(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/instagram/{id}")
    public ResponseEntity<UserSNSResponse> findInstagram(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.findInstagram(userId), HttpStatus.OK);
    }

}
