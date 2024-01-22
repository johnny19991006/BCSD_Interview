package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectRequest;
import bcsd.backend.project.pokku.service.PortfolioArchiving.PortfolioArchivingServiceImpl;
import bcsd.backend.project.pokku.service.PortfolioProject.PortfolioProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project")
public class PortfolioProjectController {

    private PortfolioProjectServiceImpl portfolioArchivingService;

    @Autowired
    public void setService(PortfolioProjectServiceImpl service){
        this.portfolioArchivingService = service;
    }

    @PostMapping
    public ResponseEntity<Boolean> addProject(@RequestBody PortfolioProjectRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.addProject(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteProject(@RequestBody PortfolioProjectRequest request) throws Exception{
        return new ResponseEntity<>(portfolioArchivingService.addProject(request), HttpStatus.OK);
    }
}
