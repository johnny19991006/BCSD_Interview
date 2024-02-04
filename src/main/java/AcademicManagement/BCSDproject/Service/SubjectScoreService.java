    package AcademicManagement.BCSDproject.Service;

    import AcademicManagement.BCSDproject.Domain.Subject;
    import AcademicManagement.BCSDproject.Domain.SubjectScore;
    import AcademicManagement.BCSDproject.Repository.SubjectRepository;
    import AcademicManagement.BCSDproject.Repository.SubjectScoreRepository;

    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    @Service
    @Transactional
    @RequiredArgsConstructor
    public class SubjectScoreService implements SubjectScoreServiceInterface{
        private final SubjectScoreRepository subjectScoreRepository;
        private final SubjectRepository subjectRepository;
        private final SemesterService semesterService;

        @Override
        public SubjectScore createSubjectScore(SubjectScore subjectScore, String subjectName)
        {
            Subject subject = subjectRepository.findBySubjectName(subjectName)
                    .orElseThrow(() -> new NoSuchElementException("Can't find"));
            subjectScore.setSubject(subject);
            subjectScoreRepository.save(subjectScore);
            semesterService.updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                    subjectScore.getSemesterEnum());
            return subjectScore;
        }

        @Override
        public List<SubjectScore> findAllSubjectScore()
        {
            return subjectScoreRepository.findAll();
        }

        @Override
        public SubjectScore findSubjectScore(String subjectName, String studentId)
        {
            return subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                    .orElseThrow(() -> new NoSuchElementException("Can't find"));
        }

        @Override
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
            semesterService.updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                    subjectScore.getSemesterEnum());

            return changeScore;
        }

        @Override
        public void deleteSubjectScore(String subjectName, String studentId)
        {
            SubjectScore subjectScore = subjectScoreRepository.findBySubjectNameAndStudentId(subjectName, studentId)
                            .orElseThrow(() -> new NoSuchElementException("Can't find"));
            semesterService.updateSemesterCredit(subjectScore.getStudentId(), subjectScore.getSemesterGradeEnum(),
                    subjectScore.getSemesterEnum());
            subjectScoreRepository.delete(subjectScore);
        }

        @Override
        public List<SubjectScore> findStudentScoreByStudentId(String studentId)
        {
            return subjectScoreRepository.findByStudentId(studentId);
        }
    }
