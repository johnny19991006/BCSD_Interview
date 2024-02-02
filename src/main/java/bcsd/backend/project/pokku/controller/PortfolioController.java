package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Image.ImageDownloadResponse;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutResponse;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingResponse;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerRequest;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerResponse;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectRequest;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectResponse;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsListResponse;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsRequest;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsResponse;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.service.Portfolio.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private PortfolioServiceImpl portfolioService;

    @Autowired
    public void setPortfolioService(PortfolioServiceImpl service){
        this.portfolioService = service;
    }

    @GetMapping(value = "/about/{id}")
    public ResponseEntity<PortfolioAboutResponse> findPortfolioAbout(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findPortfolioAbout(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/about/{id}")
    public ResponseEntity<Boolean> updatePortfolioAbout(@PathVariable(name = "id") String userId, @RequestBody PortfolioAboutRequest request) throws RuntimeException{
        if (request.getUserNameVisible() == null){
            throw new NullValueException("userNameVisible값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserEmailVisible() == null){
            throw new NullValueException("userEmailVisible값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserEducationVisible() == null){
            throw new NullValueException("userEducationVisible값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getUserTelVisible() == null){
            throw new NullValueException("userTelVisible값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.updatePortfolioAbout(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/skills/{id}")
    public ResponseEntity<PortfolioSkillsResponse> findSkills(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findSkills(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/skillslist/{category}")
    public ResponseEntity<List<PortfolioSkillsListResponse>> findSkillsList(@PathVariable(name = "category") String category) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findSkillsList(category), HttpStatus.OK);
    }

    @PostMapping(value = "/skills/{id}")
    public ResponseEntity<Boolean> addSKills(@PathVariable(name = "id") String userId, @RequestBody PortfolioSkillsRequest request) throws RuntimeException{
        if (request.getCategory() == null || request.getCategory().equals("")){
            throw new NullValueException("category값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getSkillsId() == null){
            throw new NullValueException("skillsId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.addSkills(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/skills/{id}")
    public ResponseEntity<Boolean> deleteSKills(@PathVariable(name = "id") String userId, @RequestBody PortfolioSkillsRequest request) throws RuntimeException{
        if (request.getCategory() == null || request.getCategory().equals("")){
            throw new NullValueException("category값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getSkillsId() == null){
            throw new NullValueException("skillsId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.deleteSkills(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/archiving/{id}")
    public ResponseEntity<List<PortfolioArchivingResponse>> findArchiving(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findArchiving(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/archiving/{id}")
    public ResponseEntity<Boolean> addArchiving(@PathVariable(name = "id") String userId, @RequestBody PortfolioArchivingRequest request) throws RuntimeException{
        if (request.getArchivingName() == null || request.getArchivingName().equals("")){
            throw new NullValueException("archivingName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getArchivingExplanation() == null || request.getArchivingExplanation().equals("")){
            throw new NullValueException("archivingExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.addArchiving(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/archiving/{id}")
    public ResponseEntity<Boolean> deleteArchiving(@PathVariable(name = "id") String userId, @RequestBody PortfolioArchivingRequest request) throws RuntimeException{
        if (request.getArchivingName() == null || request.getArchivingName().equals("")){
            throw new NullValueException("archivingName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getArchivingExplanation() == null || request.getArchivingExplanation().equals("")){
            throw new NullValueException("archivingExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioArchivingId() == null){
            throw new NullValueException("portfolioArchivingId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.deleteArchiving(userId, request), HttpStatus.OK);
    }

    @PutMapping(value = "/archiving/{id}")
    public ResponseEntity<Boolean> updateArchiving(@PathVariable(name = "id") String userId, @RequestBody PortfolioArchivingRequest request) throws RuntimeException{
        if (request.getArchivingName() == null || request.getArchivingName().equals("")){
            throw new NullValueException("archivingName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getArchivingExplanation() == null || request.getArchivingExplanation().equals("")){
            throw new NullValueException("archivingExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioArchivingId() == null){
            throw new NullValueException("portfolioArchivingId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.deleteArchiving(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/project/{id}")
    public ResponseEntity<List<PortfolioProjectResponse>> findProject(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findProject(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/project/{id}")
    public ResponseEntity<Boolean> addProject(@PathVariable(name = "id") String userId, @RequestBody PortfolioProjectRequest request) throws RuntimeException{
        if (request.getProjectName() == null || request.getProjectName().equals("")){
            throw new NullValueException("projectName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getProjectExplanation() == null || request.getProjectExplanation().equals("")){
            throw new NullValueException("projectExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.addProject(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/project/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable(name = "id") String userId, @RequestBody PortfolioProjectRequest request) throws RuntimeException{
        if (request.getProjectName() == null || request.getProjectName().equals("")){
            throw new NullValueException("projectName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getProjectExplanation() == null || request.getProjectExplanation().equals("")){
            throw new NullValueException("projectExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioProjectId() == null){
            throw new NullValueException("portfolioProjectId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.deleteProject(userId, request), HttpStatus.OK);
    }

    @PutMapping(value = "/project/{id}")
    public ResponseEntity<Boolean> updateProject(@PathVariable(name = "id") String userId, @RequestBody PortfolioProjectRequest request) throws RuntimeException{
        if (request.getProjectName() == null || request.getProjectName().equals("")){
            throw new NullValueException("projectName값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getProjectExplanation() == null || request.getProjectExplanation().equals("")){
            throw new NullValueException("projectExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioProjectId() == null){
            throw new NullValueException("portfolioProjectId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.updateProject(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/career/{id}")
    public ResponseEntity<List<PortfolioCareerResponse>> findCareer(@PathVariable(name = "id") String userId) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.findCareer(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/career/{id}")
    public ResponseEntity<Boolean> addCareer(@PathVariable(name = "id") String userId, @RequestBody PortfolioCareerRequest request) throws RuntimeException{
        if (request.getCareerExplanation() == null || request.getCareerExplanation().equals("")){
            throw new NullValueException("careerExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.addCareer(userId, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/career/{id}")
    public ResponseEntity<Boolean> deleteCareer(@PathVariable(name = "id") String userId, @RequestBody PortfolioCareerRequest request) throws RuntimeException{
        if (request.getCareerExplanation() == null || request.getCareerExplanation().equals("")){
            throw new NullValueException("careerExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioCareerId() == null || request.getPortfolioCareerId().equals("")){
            throw new NullValueException("portfolioCareerId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.deleteCareer(userId, request), HttpStatus.OK);
    }

    @PutMapping(value = "/career/{id}")
    public ResponseEntity<Boolean> updateCareer(@PathVariable(name = "id") String userId, @RequestBody PortfolioCareerRequest request) throws RuntimeException{
        if (request.getCareerExplanation() == null || request.getCareerExplanation().equals("")){
            throw new NullValueException("careerExplanation값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        if (request.getPortfolioCareerId() == null || request.getPortfolioCareerId().equals("")){
            throw new NullValueException("portfolioCareerId값이 비어있습니다.", null, ResCode.NULL_VALUE.value());
        }
        return new ResponseEntity<>(portfolioService.updateCareer(userId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/image/{name}")
    public ResponseEntity<ImageDownloadResponse> download(@PathVariable(name = "name") String imageName) throws RuntimeException{
        return new ResponseEntity<>(portfolioService.download(imageName), HttpStatus.OK);
    }


}
