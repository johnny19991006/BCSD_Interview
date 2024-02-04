package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Semester;
import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;
import AcademicManagement.BCSDproject.Repository.SemesterRepository;
import AcademicManagement.BCSDproject.Repository.StudentGradeInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentGradeInformationService implements StudentGradeInformationServiceInterface {
    private final StudentGradeInfoRepository studentGradeInfoRepository;
    private final SemesterRepository semesterRepository;

    @Override
    public StudentGradeInformation createStudentGradeInformation(StudentGradeInformation studentGradeInformation)
    {
        studentGradeInfoRepository.save(studentGradeInformation);
        return studentGradeInformation;
    }

    @Override
    public List<StudentGradeInformation> findAllGradeInformation()
    {
        return studentGradeInfoRepository.findAll();
    }

    @Override
    public StudentGradeInformation findStudentGradeInformation(String studentId)
    {
        return studentGradeInfoRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Can't find StudentId"));
    }

    @Override
    public StudentGradeInformation updateStudentGradeInformation(StudentGradeInformation studentGradeInformation, String studentId)
    {
        StudentGradeInformation changeGradeInformation = studentGradeInfoRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Can't find StudentId"));

        if(studentGradeInformation.getTotalCredit() != null) {
            changeGradeInformation.setTotalCredit(studentGradeInformation.getTotalCredit());
        }
        if(studentGradeInformation.getTotalMajorCredit() != null) {
            changeGradeInformation.setTotalMajorCredit(studentGradeInformation.getTotalMajorCredit());
        }
        if(studentGradeInformation.getAvgMajorScore() != null) {
            changeGradeInformation.setAvgMajorScore(studentGradeInformation.getAvgMajorScore());
        }
        if(studentGradeInformation.getTotalMajorCredit() != null) {
            changeGradeInformation.setTotalMajorCredit(studentGradeInformation.getTotalMajorCredit());
        }
        if(studentGradeInformation.getAvgGeneralScore() != null) {
            changeGradeInformation.setAvgGeneralScore(studentGradeInformation.getAvgGeneralScore());
        }
        if(studentGradeInformation.getTotalAllCredit() != null) {
            changeGradeInformation.setTotalAllCredit(studentGradeInformation.getTotalAllCredit());
        }
        if(studentGradeInformation.getAvgAllScore() != null) {
            changeGradeInformation.setAvgAllScore(studentGradeInformation.getAvgAllScore());
        }

        studentGradeInfoRepository.save(changeGradeInformation);
        return changeGradeInformation;
    }

    @Override
    public void deleteStudentGradeInformation(String studentId)
    {
        studentGradeInfoRepository.deleteById(studentId);
    }


    public void updateStudentGradeInformation(String studentId) {
        List<Semester> semesters = semesterRepository.findByStudentId(studentId);
        // 학생 아이디를 통해 학생의 학기를 모두 가져옴

        int totalMajorCredit = 0;
        float totalMajorScore = 0;
        int totalGeneralCredit = 0;
        float totalGeneralScore = 0;

        for (Semester semester : semesters) {
            totalMajorCredit += semester.getSemesterMajorCredit();
            totalMajorScore += semester.getSemesterMajorScore() * semester.getSemesterMajorCredit();
            totalGeneralCredit += semester.getSemesterGeneralCredit();
            totalGeneralScore += semester.getSemesterGeneralScore() * semester.getSemesterGeneralCredit();
        }

        float avgMajorScore = totalMajorCredit != 0 ? totalMajorScore / totalMajorCredit : 0;
        float avgGeneralScore = totalGeneralCredit != 0 ? totalGeneralScore / totalGeneralCredit : 0;

        int totalAllCredit = totalMajorCredit + totalGeneralCredit;
        float totalAllScore = totalMajorScore + totalGeneralScore;
        float avgAllScore = totalAllCredit != 0 ? totalAllScore / totalAllCredit : 0;

        StudentGradeInformation gradeInfoEntity = studentGradeInfoRepository.findByStudentId(studentId);
        gradeInfoEntity.setTotalMajorCredit(totalMajorCredit);
        gradeInfoEntity.setAvgMajorScore(avgMajorScore);
        gradeInfoEntity.setTotalGeneralCredit(totalGeneralCredit);
        gradeInfoEntity.setAvgGeneralScore(avgGeneralScore);
        gradeInfoEntity.setTotalAllCredit(totalAllCredit); // 총 학점은 이를 전공 + 교양
        gradeInfoEntity.setAvgAllScore(avgAllScore); // 총 성적은 (모든 성적) / (모든 학점)

        studentGradeInfoRepository.save(gradeInfoEntity);
    }

}
