package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.SignInRequest;
import bcsd.backend.project.pokku.dto.SignInResponse;
import bcsd.backend.project.pokku.service.SignInServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sign-in")
public class SignInController {

    private SignInServiceImpl signInService;

    @Autowired
    public void setSignService(SignInServiceImpl ssi){
        this.signInService = ssi;
    }
    @PostMapping
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request) throws Exception{
        return new ResponseEntity<>(signInService.login(request), HttpStatus.OK);
    }
}
