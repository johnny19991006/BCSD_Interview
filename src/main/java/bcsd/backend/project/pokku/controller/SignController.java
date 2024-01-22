package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Sign.SignInRequest;
import bcsd.backend.project.pokku.dto.Sign.SignInResponse;
import bcsd.backend.project.pokku.dto.Sign.SignOutRequest;
import bcsd.backend.project.pokku.dto.Sign.SignUpRequest;
import bcsd.backend.project.pokku.service.SignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sign")
public class SignController {

    private SignServiceImpl signService;

    @Autowired
    public void setSignService(SignServiceImpl service){
        this.signService = service;
    }

    @PostMapping(value = "/in")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request) throws Exception{
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/out")
    public ResponseEntity<Boolean> signin(@RequestBody SignOutRequest request) throws Exception{
        return new ResponseEntity<>(signService.logout(request), HttpStatus.OK);
    }

    @PostMapping(value = "/up")
    public ResponseEntity<Boolean> signin(@RequestBody SignUpRequest request) throws Exception{
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }
}
