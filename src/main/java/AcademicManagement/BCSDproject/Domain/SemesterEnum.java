package AcademicManagement.BCSDproject.Domain;
// Semester 내에서 사용하는 semester enum
public enum SemesterEnum {
    Fisrt_SEMESTER("1학기"),
    SECOND_SEMESTER("2학기"),
    SUMMER_SEMESTER("여름학기"),
    WINTER_SEMESTER("겨울학기");

    public String semester;

    SemesterEnum(String semester)
    {
        this.semester = semester;
    }

    public String getSemester()
    {
        return semester;
    }
}
