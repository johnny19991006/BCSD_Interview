package Domain;

public enum CategoryEnum {
    MAJOR("전공"),
    GENERAL("교양");

    private String category;

    CategoryEnum(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
}
