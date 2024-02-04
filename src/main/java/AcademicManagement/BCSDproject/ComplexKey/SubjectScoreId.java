package AcademicManagement.BCSDproject.ComplexKey;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubjectScoreId implements Serializable {
    private String studentId;
    private String subjectName;
}
