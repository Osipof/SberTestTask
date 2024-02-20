package sbp.sberuniversity.jcore.draft.api.constants;

import java.util.Arrays;

public enum AllowedMethods {

    LIST("list"),
    FIND_BY_CRITERIA("find"),
    SAVE("save"),
    DELETE("delete");

    private final String methodName;

    AllowedMethods(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public static AllowedMethods getEnum(String method) {
        return Arrays.stream(AllowedMethods.values())
                .filter(elem -> elem.methodName.equals(method))
                .findAny()
                .orElse(null);
    }

}
