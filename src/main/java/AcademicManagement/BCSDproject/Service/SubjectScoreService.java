package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Domain.SubjectScore;
import AcademicManagement.BCSDproject.Repository.SubjectScoreRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectScoreService {
    private final SubjectScoreRepository subjectScoreRepository;

    public SubjectScore createSubjectScore(SubjectScore subjectScore)
    {
        subjectScoreRepository.save(subjectScore);
        return subjectScore;
    }

    public List<SubjectScore> findAllSubjectScore()
    {
        return subjectScoreRepository.findAll();
    }

    public SubjectScore findSubjectScore(String subjectName, String studentId)
    {
        return subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));
    }

    public SubjectScore updateSubjectScore(SubjectScore subjectScore, String subjectName, String studentId)
    {
        SubjectScore changeScore = subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));

        changeScore.setSubjectScoreEnum(subjectScore.getSubjectScoreEnum());
        changeScore.setSubjectScore(subjectScore.getSubjectScore());
        changeScore.setSubjectRetake(subjectScore.getSubjectRetake());

        subjectScoreRepository.save(changeScore);

        return changeScore;
    }

    public void deleteSubjectScore(String subjectName, String studentId)
    {
        SubjectScore subjectScore = subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                        .orElseThrow(() -> new NoSuchElementException("Can't find"));
        subjectScoreRepository.delete(subjectScore);
    }

    public List<SubjectScore> findStudentScoreByStudentId(String studentId)
    {
        return subjectScoreRepository.findByStudentId(studentId);
    }
}
