package AcademicManagement.BCSDproject.Service;

import AcademicManagement.BCSDproject.Domain.Student;
import AcademicManagement.BCSDproject.Domain.StudentGradeInformation;
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


}
