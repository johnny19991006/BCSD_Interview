package com.example.studyroom;

public enum Message {
    ALREADY_OCCUPIED("이미 사용하고 있는 자리입니다!"),
    INCORRECT_SEAT("올바르지 않은 좌석입니다!"),
    NON_EXISTENT_USER("존재하지 않는 사용자입니다!"),
    NON_USE_SEAT("사용중이 아닌 좌석입니다!"),
    DIFFERENT_PREVIOUS_SEAT("이전 좌석과 다릅니다!"),
    SAME_PREVIOUS_SEAT("변경하려는 좌석이 이전 좌석과 동일합니다!"),
    ALREADY_USE("이미 선택한 자리가 있습니다!");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    };
}
