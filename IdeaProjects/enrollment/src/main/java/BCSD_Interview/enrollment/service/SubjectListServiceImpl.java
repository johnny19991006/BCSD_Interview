package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.SubjectList;
import BCSD_Interview.enrollment.domain.User;
import BCSD_Interview.enrollment.repository.SubjectListRepository;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SubjectListServiceImpl implements SubjectListService {
    private final SubjectListRepository subjectListRepository;

    @Autowired
    public SubjectListServiceImpl(SubjectListRepository subjectListRepository) { this.subjectListRepository = subjectListRepository; }

    @Override
    public List<SubjectList> getAllLists() throws SQLException {
        return subjectListRepository.getAllList();
    }

    @Override
    public List<SubjectList> getFilteredList(User signedInUser) throws SQLException {
        return subjectListRepository.getFilteredList(signedInUser);
    }
}