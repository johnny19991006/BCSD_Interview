package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Domain.SubjectScore;
import AcademicManagement.BCSDproject.Enum.SemesterEnum;
import AcademicManagement.BCSDproject.Enum.SemesterGradeEnum;
import AcademicManagement.BCSDproject.Repository.SemesterRepository;
import AcademicManagement.BCSDproject.Repository.SubjectRepository;
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
    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
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
        changeScore.setSemesterGradeEnum(subjectScore.getSemesterGradeEnum());
        changeScore.setSemesterEnum(subjectScore.getSemesterEnum());

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

    public void updateSemesterCredit(String studentId, SemesterGradeEnum semesterGradeEnum,
                                     SemesterEnum semesterEnum)
    {
        List<SubjectScore> subjectScores = subjectScoreRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);

        int totalCredit = subjectScores.stream()
                .mapToInt(subjectScore -> subjectRepository.findBySubjectName(subjectScore.getSubjectName()).get().getCredit())
                .sum();
        Semester semesterEntity = semesterRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        semesterEntity.setSemesterCredit(totalCredit);
    }
}
