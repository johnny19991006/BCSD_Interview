package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Subject;
import AcademicManagement.BCSDproject.Repository.SemesterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

// Semester 내에서 현재 필요한 기능
// 성적 확인 가능하게 추가
@Service
@Transactional
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;

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
}
