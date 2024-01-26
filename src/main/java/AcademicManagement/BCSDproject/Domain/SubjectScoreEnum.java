package AcademicManagement.BCSDproject.Domain;

public enum SubjectScoreEnum {
    APLUS("A+"), AZERO("A0"), BPLUS("B+"), BZERO("B0"), CPLUS("C+")
    ,CZERO("C0"), DPLUS("D+"), DZERO("D0"), F("F");

    private String grade;

    SubjectScoreEnum(String grade)
    {
        this.grade = grade;
    }

    String getGrade()
    {
        return grade;
    }
}
