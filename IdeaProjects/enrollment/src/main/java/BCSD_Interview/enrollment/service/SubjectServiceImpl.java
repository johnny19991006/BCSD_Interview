package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.domain.Subject;
import BCSD_Interview.enrollment.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject insertSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubject_Record() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectBySubjectId(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public void updateAcademic_Year(Long subjectId, Integer academic_year) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setAcademic_Year(academic_year);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateSemester(Long subjectId, String semester) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setSemester(semester);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateGrade(Long subjectId, Integer grade) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setGrade(grade);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateSubject_Code(Long subjectId, String subject_code) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setSubject_code(subject_code);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateSubject_Name(Long subjectId, String subject_name) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setSubject_name(subject_name);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateClass_Num(Long subjectId, Integer class_num) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setClass_num(class_num);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateProfessor(Long subjectId, String professor) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setProfessor(professor);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateCompletion_Division(Long subjectId, String completion_division) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setCompletion_division(completion_division);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateCredit(Long subjectId, Integer credit) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setCredit(credit);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateMark(Long subjectId, String mark) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setMark(mark);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateLecture_Num(Long subjectId, Integer lecture_num) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setLecture_num(lecture_num);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateLecture_Time(Long subjectId, String lecture_time) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setLecture_time(lecture_time);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateRepetition(Long subjectId, String repetition) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setRepetition(repetition);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void updateStudent(Long subjectId, Long user_id) {
        Subject existingSubject = subjectRepository.findById(subjectId).orElse(null);
        if (existingSubject != null) {
            existingSubject.setStudent(user_id);
            subjectRepository.save(existingSubject);
        }
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}