package BCSD_Interview.enrollment.controller;

import BCSD_Interview.enrollment.domain.SubjectList;
import BCSD_Interview.enrollment.domain.User;
import BCSD_Interview.enrollment.service.SubjectListService;
import BCSD_Interview.enrollment.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class SubjectListController {
    @Autowired
    SubjectListService subjectListService;

    @GetMapping("")
    public List<SubjectList> getAllLists() throws SQLException {
        return subjectListService.getAllLists();
    }

    @GetMapping("/filtering")
    public List<SubjectList> getFilteredList(Model model, @AuthenticationPrincipal User signedInUser) throws SQLException {
        try {
            List<SubjectList> filteredSubjectList = subjectListService.getFilteredList(signedInUser);

            model.addAttribute("subjectList", filteredSubjectList);
            return filteredSubjectList;
        } catch (SQLException e) {
            throw e;
        }
    }
}
