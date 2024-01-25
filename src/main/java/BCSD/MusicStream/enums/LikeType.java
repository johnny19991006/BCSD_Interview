package BCSD.MusicStream.enums;

public enum LikeType {
    NEUTRAL(0),
    LIKE(1),
    DISLIKE(-1);

    private final int value;

    LikeType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static LikeType fromValue(int value) {
        for (LikeType type : values()) {
            if (type.getValue() == value) return type;
        }
        return NEUTRAL;
    }
}
