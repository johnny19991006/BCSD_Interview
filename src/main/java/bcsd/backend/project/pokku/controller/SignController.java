package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Sign.SignInRequest;
import bcsd.backend.project.pokku.dto.Sign.SignInResponse;
import bcsd.backend.project.pokku.dto.Sign.SignOutRequest;
import bcsd.backend.project.pokku.dto.Sign.SignUpRequest;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.service.Sign.SignServiceImpl;
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
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request) throws RuntimeException{
        if (request.getUserId() == null || request.getUserId().equals("")){
            throw new NullValueException("userId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserPassword() == null || request.getUserPassword().equals("")){
            throw new NullValueException("userPassword값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/out")
    public ResponseEntity<Boolean> signout(@RequestBody SignOutRequest request) throws RuntimeException{
        if (request.getUserId() == null || request.getUserId().equals("")){
            throw new NullValueException("userId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getToken() == null || request.getToken().equals("")){
            throw new NullValueException("token값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(signService.logout(request), HttpStatus.OK);
    }

    @PostMapping(value = "/up")
    public ResponseEntity<Boolean> signup(@RequestBody SignUpRequest request) throws RuntimeException{
        if (request.getUserName() == null || request.getUserName().equals("")){
            throw new NullValueException("userName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserPassword() == null || request.getUserPassword().equals("")){
            throw new NullValueException("userPassword값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserId() == null || request.getUserId().equals("")){
            throw new NullValueException("userId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserTel() == null || request.getUserTel().equals("")){
            throw new NullValueException("userTel값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserNickname() == null || request.getUserNickname().equals("")){
            throw new NullValueException("userNickname값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserEmail() == null || request.getUserEmail().equals("")){
            throw new NullValueException("userEmail값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserBirth() == null || request.getUserBirth().equals("")){
            throw new NullValueException("userBirth값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserEducation() == null || request.getUserEducation().equals("")){
            throw new NullValueException("userEducation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }
}
