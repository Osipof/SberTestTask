package sbp.sberuniversity.jcore.draft.models;

import java.util.Arrays;

public enum DifficultyLevel {

    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String value;

    DifficultyLevel(String value) {
        this.value = value;
    }

    public static DifficultyLevel fromValue(String value) {
        return Arrays.stream(DifficultyLevel.values())
                .filter(elem -> elem.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Incorrect Difficulty level value!"));
    }

}
