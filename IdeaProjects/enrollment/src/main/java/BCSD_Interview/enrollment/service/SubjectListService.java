package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.SubjectList;
import BCSD_Interview.enrollment.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface SubjectListService {
    List<SubjectList> getAllLists() throws SQLException;
    List<SubjectList> getFilteredList(User signedInUser) throws SQLException;
}
