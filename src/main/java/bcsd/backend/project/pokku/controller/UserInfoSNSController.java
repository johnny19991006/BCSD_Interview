package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSRequest;
import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSResponse;
import bcsd.backend.project.pokku.service.UserInfoSNS.UserInfoSNSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sns")
public class UserInfoSNSController {

    private UserInfoSNSServiceImpl userInfoSNSService;

    @Autowired
    public void setSignService(UserInfoSNSServiceImpl ssi){
        this.userInfoSNSService = ssi;
    }

    @PostMapping
    public ResponseEntity<UserInfoSNSResponse> findUserSNS(@RequestBody UserInfoSNSRequest request) throws Exception{
        return new ResponseEntity<>(userInfoSNSService.findSNS(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateUserSNS(@RequestBody UserInfoSNSRequest request) throws Exception{
        return new ResponseEntity<>(userInfoSNSService.UpdateSNS(request), HttpStatus.OK);
    }

}
