package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAboutResponse;
import bcsd.backend.project.pokku.dto.SignInRequest;
import bcsd.backend.project.pokku.dto.SignInResponse;
import bcsd.backend.project.pokku.service.PortfolioAboutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/about")
public class PortfolioAboutController {
    private PortfolioAboutServiceImpl portfolioAboutService;

    @Autowired
    public void setPortfolioAboutService(PortfolioAboutServiceImpl service){
        this.portfolioAboutService = service;
    }

    @PutMapping
    public ResponseEntity<Boolean> updatePortfolioAbout(@RequestBody PortfolioAboutRequest request) throws Exception{
        return new ResponseEntity<>(portfolioAboutService.updatePortfolioAbout(request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PortfolioAboutResponse> findPortfolioAbout(@RequestBody PortfolioAboutRequest request) throws Exception{
        return new ResponseEntity<>(portfolioAboutService.findPortfolioAbout(request), HttpStatus.OK);
    }
}
