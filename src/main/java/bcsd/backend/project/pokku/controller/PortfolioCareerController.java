package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerRequest;
import bcsd.backend.project.pokku.service.PortfolioAbout.PortfolioAboutServiceImpl;
import bcsd.backend.project.pokku.service.PortfolioArchiving.PortfolioArchivingService;
import bcsd.backend.project.pokku.service.PortfolioArchiving.PortfolioArchivingServiceImpl;
import bcsd.backend.project.pokku.service.PortfolioCareer.PortfolioCareerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/career")
public class PortfolioCareerController {

    private PortfolioCareerServiceImpl portfolioArchivingService;

    @Autowired
    public void setService(PortfolioCareerServiceImpl service){
        this.portfolioArchivingService = service;
    }

    @PostMapping
    public ResponseEntity<Boolean> addArchiving(@RequestBody PortfolioCareerRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.addCareer(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteArchiving(@RequestBody PortfolioCareerRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.deleteCareer(request), HttpStatus.OK);
    }
}
