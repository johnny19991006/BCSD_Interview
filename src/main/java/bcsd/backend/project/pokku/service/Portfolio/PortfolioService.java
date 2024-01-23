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
    public PortfolioAboutResponse findPortfolioAbout(String userId) throws Exception;
    public Boolean updatePortfolioAbout(String userId, PortfolioAboutRequest request) throws Exception;

    // skills
    public PortfolioSkillsResponse findSkills(String userId) throws Exception;
    public Boolean addSkills(String userId, PortfolioSkillsRequest request) throws Exception;
    public Boolean deleteSkills(String userId, PortfolioSkillsRequest request) throws Exception;
    public List<PortfolioSkillsListResponse> findSkillsList(String imageCategory) throws Exception;

    // archiving
    public List<PortfolioArchivingResponse> findArchiving(String userId) throws Exception;
    public Boolean addArchiving(String userId, PortfolioArchivingRequest request) throws Exception;
    public Boolean updateArchiving(String userId, PortfolioArchivingRequest request) throws Exception;
    public Boolean deleteArchiving(String userId, PortfolioArchivingRequest request) throws Exception;

    // career
    public List<PortfolioCareerResponse> findCareer(String userId) throws Exception;
    public Boolean addCareer(String userId, PortfolioCareerRequest request) throws Exception;
    public Boolean updateCareer(String userId, PortfolioCareerRequest request) throws Exception;
    public Boolean deleteCareer(String userId, PortfolioCareerRequest request) throws Exception;

    // project
    public List<PortfolioProjectResponse> findProject(String userId) throws Exception;
    public Boolean addProject(String userId, PortfolioProjectRequest request) throws Exception;
    public Boolean updateProject(String userId, PortfolioProjectRequest request) throws Exception;
    public Boolean deleteProject(String userId, PortfolioProjectRequest request) throws Exception;

    // image
    public ImageDownloadResponse download(String imageName) throws Exception;
}
