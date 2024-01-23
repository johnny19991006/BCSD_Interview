package Domain;
// Semester 내에서 사용하는 semester_grade enum
public enum SemesterGradeEnum {
    FIRST_YEAR("1학년"),
    SECOND_YEAR("2학년"),
    THIRD_YEAR("3학년"),
    FOURTH_YEAR("4학년"),
    EXCESS_SEMESTER("초과학기");

    private String grade;

    SemesterGradeEnum(String grade)
    {
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }
}
