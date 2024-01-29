package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.Subject;
import BCSD_Interview.enrollment.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject insertSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubject_Record() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectBySubjectId(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
