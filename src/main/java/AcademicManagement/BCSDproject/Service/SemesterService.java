package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Subject;
import AcademicManagement.BCSDproject.Domain.SubjectScore;
import AcademicManagement.BCSDproject.Enum.CategoryEnum;
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
import java.util.Optional;

// Semester 내에서 현재 필요한 기능
// 성적 확인 가능하게 추가,
@Service
@Transactional
@RequiredArgsConstructor
public class SemesterService implements SemesterServiceInterface{
    private final SemesterRepository semesterRepository;
    private final SubjectScoreRepository subjectScoreRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Semester createSemester(Semester semester)
    {
        semesterRepository.save(semester);
        return semester;
    }

    @Override
    public List<Semester> findAllSemester()
    {
        return semesterRepository.findAll();
    }

    public Semester findSemester(int semesterId)
    {
        return semesterRepository.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));
    }

    @Override
    public Semester updateSemester(Semester semester, int semesterId)
    {
        Semester changeSemester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));

        if(semester.getSemesterYear() != null) {
            changeSemester.setSemesterYear(semester.getSemesterYear());
        }
        if(semester.getSemesterGradeEnum() != null) {
            changeSemester.setSemesterGradeEnum(semester.getSemesterGradeEnum());
        }
        if(semester.getSemesterEnum() != null) {
            changeSemester.setSemesterEnum(semester.getSemesterEnum());
        }
        if(semester.getSemesterScore() != null) {
            changeSemester.setSemesterScore(semester.getSemesterScore());
        }
        if(semester.getSemesterCredit() != null) {
            changeSemester.setSemesterCredit(semester.getSemesterCredit());
        }

        semesterRepository.save(changeSemester);

        return new Semester(semester);
    }

    @Override
    public void deleteSemester(int semesterId)
    {
        semesterRepository.deleteById(semesterId);
    }

    @Override
    public List<Semester> studentSemester(String studentId)
    {
        return semesterRepository.findByStudentId(studentId);
    }


    @Override
    public void updateSemesterCredit(String studentId, SemesterGradeEnum semesterGradeEnum,
                                     SemesterEnum semesterEnum)
    {
        List<SubjectScore> subjectScores = subjectScoreRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        // 학생의 이름, 학년, 학기를 이용하여 SubjectScore 리스트 생성

        int totalCredit = subjectScores.stream()
                .mapToInt(subjectScore -> subjectRepository.findBySubjectName(subjectScore.getSubjectName()).get().getCredit())
                .sum();

        int totalCreditSum = 0; // 전체 학점
        float totalScoreSum = 0; // 전체 성적
        int totalMajorCreditSum = 0; // 전공 학점
        float totalMajorScoreSum = 0; // 전공 성적
        int totalGeneralCreditSum = 0; // 교양 학점
        float totalGeneralScoreSum = 0; // 교양 성적

        for (SubjectScore subjectScore : subjectScores) {
            totalScoreSum += subjectScore.getSubject().getCredit() * subjectScore.getSubjectScore();
            totalCreditSum += subjectScore.getSubject().getCredit();

            if(subjectScore.getSubject().getCategoryEnum() == CategoryEnum.MAJOR)
            {
                totalMajorCreditSum += subjectScore.getSubject().getCredit();
                totalMajorScoreSum += subjectScore.getSubjectScore() * subjectScore.getSubject().getCredit();
            }
            else
            {
                totalGeneralCreditSum += subjectScore.getSubject().getCredit();
                totalGeneralScoreSum += subjectScore.getSubjectScore() * subjectScore.getSubject().getCredit();
            }
        }
        // 전체 성적 계산
        float semesterScore = totalScoreSum / totalCreditSum;
        float semesterMajorScore = totalMajorScoreSum / totalMajorCreditSum;
        float semesterGeneralScore = totalGeneralScoreSum / totalGeneralCreditSum;

        // 스트림을 이용하여 리스트의 값에 있는 크레딧 값을 가져오고, 이를 합하도록 함(이때 subjectRepository의 크레딧을 가져옴)

        Semester semesterEntity = semesterRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        // 이를 새로운 학기를 생성하여 Set을 이용해, 총 학점을 넣도록 하였음.

        semesterEntity.setSemesterCredit(totalCredit);
        semesterEntity.setSemesterScore(semesterScore);
        semesterEntity.setSemesterMajorCredit(totalMajorCreditSum);
        semesterEntity.setSemesterMajorScore(semesterMajorScore);
        semesterEntity.setSemesterGeneralCredit(totalGeneralCreditSum);
        semesterEntity.setSemesterGeneralScore(semesterGeneralScore);

        semesterRepository.save(semesterEntity);
    }
}
