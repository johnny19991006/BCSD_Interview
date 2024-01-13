package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.SignUpRequest;
import bcsd.backend.project.pokku.service.SignUpService;
import bcsd.backend.project.pokku.service.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sign-up")
public class SignUpController {
    private SignUpServiceImpl signUpService;

    @Autowired
    public void setSignService(SignUpServiceImpl ssi){
        this.signUpService = ssi;
    }
    @PostMapping
    public ResponseEntity<Boolean> signup(@RequestBody SignUpRequest request) throws Exception{
        return new ResponseEntity<>(signUpService.register(request), HttpStatus.OK);
    }
}
