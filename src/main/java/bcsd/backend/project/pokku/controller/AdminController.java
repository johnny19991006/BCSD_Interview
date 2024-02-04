package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.service.Image.ImageServiceImpl;
import bcsd.backend.project.pokku.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(userService.findUsers(userId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(userService.DeleteUsers(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> uploadImage(@ModelAttribute ImageUploadRequest request) throws RuntimeException{
        if (request.getCategory() == null || request.getCategory().equals("")){
            throw new NullValueException("category값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getName() == null || request.getName().equals("")){
            throw new NullValueException("name값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getImage() == null){
            throw new NullValueException("image값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(imageService.upload(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/image/{name}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable(name = "name") String imageName) throws RuntimeException{
        return new ResponseEntity<>(imageService.deleteImage(imageName), HttpStatus.OK);
    }
}
