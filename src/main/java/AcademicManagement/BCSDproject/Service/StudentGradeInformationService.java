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

        changeGradeInformation.setTotalCredit(studentGradeInformation.getTotalCredit());
        changeGradeInformation.setTotalMajorCredit(studentGradeInformation.getTotalMajorCredit());
        changeGradeInformation.setAvgMajorScore(studentGradeInformation.getAvgMajorScore());
        changeGradeInformation.setTotalMajorCredit(studentGradeInformation.getTotalMajorCredit());
        changeGradeInformation.setAvgGeneralScore(studentGradeInformation.getAvgGeneralScore());
        changeGradeInformation.setTotalAllCredit(studentGradeInformation.getTotalAllCredit());
        changeGradeInformation.setAvgAllScore(studentGradeInformation.getAvgAllScore());

        studentGradeInfoRepository.save(changeGradeInformation);
        return changeGradeInformation;
    }

    @Override
    public void deleteStudentGradeInformation(String studentId)
    {
        studentGradeInfoRepository.deleteById(studentId);
    }


}
