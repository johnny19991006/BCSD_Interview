package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.service.Image.ImageServiceImpl;
import bcsd.backend.project.pokku.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private ImageServiceImpl imageService;
    private UserServiceImpl userService;

    @Autowired
    public void setImageService(ImageServiceImpl imageService){
        this.imageService = imageService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.findUsers(userId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id") String userId) throws Exception{
        return new ResponseEntity<>(userService.DeleteUsers(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<Boolean> uploadImage(ImageUploadRequest request) throws Exception{
        return new ResponseEntity<>(imageService.upload(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/image/{name}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable(name = "name") String imageName) throws Exception{
        return new ResponseEntity<>(imageService.deleteImage(imageName), HttpStatus.OK);
    }
}
