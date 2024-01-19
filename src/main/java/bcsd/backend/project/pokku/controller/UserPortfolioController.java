package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Portfolio.PortfolioRequest;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioResponse;
import bcsd.backend.project.pokku.service.Portfolio.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/portfolio")
public class UserPortfolioController {

    private PortfolioServiceImpl portfolioService;

    @Autowired
    public void setSignService(PortfolioServiceImpl ssi){
        this.portfolioService = ssi;
    }

    @PostMapping
    public ResponseEntity<Boolean> addSkills(@RequestBody PortfolioRequest request) throws Exception{
        return new ResponseEntity<>(portfolioService.addSkills(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteSkills(@RequestBody PortfolioRequest request) throws Exception{
        return new ResponseEntity<>(portfolioService.deleteSkills(request), HttpStatus.OK);
    }

}
