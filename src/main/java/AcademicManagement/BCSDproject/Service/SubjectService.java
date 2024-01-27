package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Subject;
import AcademicManagement.BCSDproject.Repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService implements SubjectServiceInterface{
    private final SubjectRepository subjectRepository;
    @Override
    public Subject createSubject(Subject subject)
    {
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public List<Subject> findAllSubject()
    {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(String subjectName)
    {
        return subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new NoSuchElementException("Can't Find."));
    }

    @Override
    public Subject updateSubject(Subject subject, String subjectName)
    {
        Subject changeSubject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));

        changeSubject.setSubjectName(subject.getSubjectName());
        changeSubject.setProfessorName(subject.getProfessorName());
        changeSubject.setCategoryEnum(subject.getCategoryEnum());
        changeSubject.setCredit(subject.getCredit());

        subjectRepository.save(changeSubject);

        return new Subject(subject);
    }

    @Override
    public void deleteSubject(String subjectName)
    {
        subjectRepository.deleteById(subjectName);
    }
}
