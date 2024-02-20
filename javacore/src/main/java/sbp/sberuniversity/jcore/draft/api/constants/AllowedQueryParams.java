package sbp.sberuniversity.jcore.draft.api.constants;

public enum AllowedQueryParams {

    FIND_BY_ID("id"),
    FIND_BY_LEVEL("level"),
    FIND_BY_MULTIPLE_CHOICE("multipleChoice");

    private final String queryParam;

    AllowedQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }

}
