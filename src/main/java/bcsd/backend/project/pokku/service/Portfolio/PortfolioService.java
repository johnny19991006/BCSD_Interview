package bcsd.backend.project.pokku.service.Portfolio;

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

import java.util.List;

public interface PortfolioService {

    // about
    public PortfolioAboutResponse findPortfolioAbout(String userId) throws RuntimeException;
    public Boolean updatePortfolioAbout(String userId, PortfolioAboutRequest request) throws RuntimeException;

    // skills
    public PortfolioSkillsResponse findSkills(String userId) throws RuntimeException;
    public Boolean addSkills(String userId, PortfolioSkillsRequest request) throws RuntimeException;
    public Boolean deleteSkills(String userId, PortfolioSkillsRequest request) throws RuntimeException;
    public List<PortfolioSkillsListResponse> findSkillsList(String imageCategory) throws RuntimeException;

    // archiving
    public List<PortfolioArchivingResponse> findArchiving(String userId) throws RuntimeException;
    public Boolean addArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException;
    public Boolean updateArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException;
    public Boolean deleteArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException;

    // career
    public List<PortfolioCareerResponse> findCareer(String userId) throws RuntimeException;
    public Boolean addCareer(String userId, PortfolioCareerRequest request) throws RuntimeException;
    public Boolean updateCareer(String userId, PortfolioCareerRequest request) throws RuntimeException;
    public Boolean deleteCareer(String userId, PortfolioCareerRequest request) throws RuntimeException;

    // project
    public List<PortfolioProjectResponse> findProject(String userId) throws RuntimeException;
    public Boolean addProject(String userId, PortfolioProjectRequest request) throws RuntimeException;
    public Boolean updateProject(String userId, PortfolioProjectRequest request) throws RuntimeException;
    public Boolean deleteProject(String userId, PortfolioProjectRequest request) throws RuntimeException;

    // image
    public ImageDownloadResponse download(String imageName) throws RuntimeException;
}
