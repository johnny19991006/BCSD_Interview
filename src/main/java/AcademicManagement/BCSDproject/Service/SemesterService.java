package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;
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
    private final StudentGradeInformationService studentGradeInformationService;

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
                .orElseThrow(() -> new NoSuchElementException("Can't find Semester"));
    }

    @Override
    public Semester updateSemester(Semester semester, int semesterId)
    {
        Semester changeSemester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("Can't find Semester"));

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
        int totalMajorCredit = 0; // 전공 학점
        float totalMajorScore = 0; // 전공 성적
        int totalGeneralCredit = 0; // 교양 학점
        float totalGeneralScore = 0; // 교양 성적

        for (SubjectScore subjectScore : subjectScores) {
            int credit = subjectScore.getSubject().getCredit(); // 기본적으로 과목에서 학점 가져옴
            float score = subjectScore.getSubjectScore(); // 과목 성적 가져옴
            totalScoreSum += credit * score; // 성적 계산을 위해 다 더함
            totalCreditSum += credit; // 학기에서 얻은 모든 학점 더함

            CategoryEnum categoryEnum = subjectScore.getSubject().getCategoryEnum(); // 과목에서 카테고리 가져와서 전공, 교양 나눔
            if (categoryEnum == CategoryEnum.MAJOR) {
                totalMajorScore += credit * score;
                totalMajorCredit += credit;
            } else if (categoryEnum == CategoryEnum.GENERAL) {
                totalGeneralScore += credit * score;
                totalGeneralCredit += credit;
            }
        }
        // 전체 성적 계산, 각 0이 아니라면 값에 맞게 점수 추가
        float semesterScore = totalCreditSum != 0 ? totalScoreSum / totalCreditSum : 0;
        float majorAverageScore = totalMajorCredit != 0 ? totalMajorScore / totalMajorCredit : 0;
        float generalAverageScore = totalGeneralCredit != 0 ? totalGeneralScore / totalGeneralCredit : 0;

        // 스트림을 이용하여 리스트의 값에 있는 크레딧 값을 가져오고, 이를 합하도록 함(이때 subjectRepository의 크레딧을 가져옴)

        Semester semesterEntity = semesterRepository.findByStudentIdAndSemesterGradeEnumAndSemesterEnum
                (studentId, semesterGradeEnum, semesterEnum);
        // 이를 새로운 학기를 생성하여 Setter를 이용해, 총 학점을 넣도록 하였음.

        semesterEntity.setSemesterCredit(totalCredit);
        semesterEntity.setSemesterScore(semesterScore);
        semesterEntity.setSemesterMajorCredit(totalMajorCredit);
        semesterEntity.setSemesterMajorScore(majorAverageScore);
        semesterEntity.setSemesterGeneralCredit(totalGeneralCredit);
        semesterEntity.setSemesterGeneralScore(generalAverageScore);

        semesterRepository.save(semesterEntity);

        // 이는 추가적으로 학생 아이디를 이용하여 전체 학기에 대한 점수를 출력할 수 있도록 함
        studentGradeInformationService.updateStudentGradeInformation(studentId);
    }
}
