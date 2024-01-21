package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.service.PortfolioAbout.PortfolioAboutServiceImpl;
import bcsd.backend.project.pokku.service.PortfolioArchiving.PortfolioArchivingService;
import bcsd.backend.project.pokku.service.PortfolioArchiving.PortfolioArchivingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/archiving")
public class PortfolioArchivingController {

    private PortfolioArchivingServiceImpl portfolioArchivingService;

    @Autowired
    public void setService(PortfolioArchivingServiceImpl service){
        this.portfolioArchivingService = service;
    }

    @PostMapping
    public ResponseEntity<Boolean> addArchiving(@RequestBody PortfolioArchivingRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.addArchiving(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteArchiving(@RequestBody PortfolioArchivingRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.deleteArchiving(request), HttpStatus.OK);
    }
}
