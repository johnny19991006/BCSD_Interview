package bcsd.backend.project.pokku.exception;

public enum ResCode {
    SUCCESS(0), NO_SUCH_DATA(-1), INPUT_MISMATCH(-2), DUPLICATE_KEY(-3), NULL_VALUE(-4), NOT_SUPPORT(-5),
    SIGNATURE(-6), MALFORMED(-7), EXPIRED(-8), UNKNOWN(-99);
    // 성공했을 때, 일치하는 data가 없을 때, Not Null Key가 Null 값으로 왔을 때, 다른 exception이 발생했을 때

    private final int value;

    ResCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}