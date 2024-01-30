package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Subject;
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

// Semester 내에서 현재 필요한 기능
// 성적 확인 가능하게 추가,
@Service
@Transactional
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final SubjectScoreRepository subjectScoreRepository;
    private final SubjectRepository subjectRepository;

    public Semester createSemester(Semester semester)
    {
        semesterRepository.save(semester);
        return semester;
    }

    public List<Semester> findAllSemester()
    {
        return semesterRepository.findAll();
    }

    public Semester findSemester(int semesterId)
    {
        return semesterRepository.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));
    }

    public Semester updateSemester(Semester semester, int semesterId)
    {
        Semester changeSemester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("Can't find"));

        changeSemester.setSemesterYear(semester.getSemesterYear());
        changeSemester.setSemesterGradeEnum(semester.getSemesterGradeEnum());
        changeSemester.setSemesterEnum(semester.getSemesterEnum());
        changeSemester.setSemesterScore(semester.getSemesterScore());
        changeSemester.setSemesterCredit(semester.getSemesterCredit());

        semesterRepository.save(changeSemester);

        return new Semester(semester);
    }

    public void deleteSemester(int semesterId)
    {
        semesterRepository.deleteById(semesterId);
    }

    public List<Semester> studentSemester(String studentId)
    {
        return semesterRepository.findByStudentId(studentId);
    }

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

        semesterRepository.save(semesterEntity);
    }
}
