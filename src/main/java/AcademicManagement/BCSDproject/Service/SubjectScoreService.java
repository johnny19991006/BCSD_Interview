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
        updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                subjectScore.getSemesterEnum());
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
        updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                subjectScore.getSemesterEnum());

        return changeScore;
    }

    public void deleteSubjectScore(String subjectName, String studentId)
    {
        SubjectScore subjectScore = subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                        .orElseThrow(() -> new NoSuchElementException("Can't find"));
        updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                subjectScore.getSemesterEnum());
        subjectScoreRepository.delete(subjectScore);
    }

    public List<SubjectScore> findStudentScoreByStudentId(String studentId)
    {
        return subjectScoreRepository.findByStudentId(studentId);
    }

    //학년과 학기, 학생의 아이디를 이용하여 학점을 추가하도록 하는 메소드
    public void updateSemesterCredit(String studentId, SemesterGradeEnum semesterGradeEnum,
                                     SemesterEnum semesterEnum)
    {
        List<SubjectScore> subjectScores = subjectScoreRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        // 학생의 이름, 학년, 학기를 이용하여 SubjectScore 리스트 생성

        int totalCredit = subjectScores.stream()
                .mapToInt(subjectScore -> subjectRepository.findBySubjectName(subjectScore.getSubjectName()).get().getCredit())
                .sum();
        // 스트림을 이용하여 리스트의 값에 있는 크레딧 값을 가져오고, 이를 합하도록 함(이때 subjectRepository의 크레딧을 가져옴)

        Semester semesterEntity = semesterRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        // 이를 새로운 학기를 생성하여 Set을 이용해, 총 학점을 넣도록 하였음.

        semesterEntity.setSemesterCredit(totalCredit);
    }
}
