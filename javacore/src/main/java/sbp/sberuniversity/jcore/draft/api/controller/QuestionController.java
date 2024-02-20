package sbp.sberuniversity.jcore.draft.api.controller;

import sbp.sberuniversity.jcore.draft.api.constants.AllowedMethods;
import sbp.sberuniversity.jcore.draft.api.constants.AllowedQueryParams;
import sbp.sberuniversity.jcore.draft.api.constants.HttpStatus;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;
import sbp.sberuniversity.jcore.draft.models.*;
import sbp.sberuniversity.jcore.draft.services.MarshallingService;
import sbp.sberuniversity.jcore.draft.services.QuestionService;
import sbp.sberuniversity.jcore.draft.services.ResponseSenderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class QuestionController extends Controller {

    private final QuestionService questionService;

    public QuestionController(Map<String, Map<AllowedMethods, List<AllowedQueryParams>>> supportedRoutes,
                              ResponseSenderService responseSenderService, QuestionService questionService) {
        super(supportedRoutes, responseSenderService);
        this.questionService = questionService;
    }

    @Override
    public void invokeControllerMethod(HttpExchangeAdapter adapter, AllowedMethods methodToInvoke) throws IOException {
        switch (methodToInvoke) {
            case LIST -> getAll(adapter);
            case FIND_BY_CRITERIA -> {
                checkQueryParamsPairing(adapter);
                findByCriteria(adapter);
            }
            case SAVE -> save(adapter);
            case DELETE -> delete(adapter);
            default -> responseSenderService.sendError(HttpStatus.METHOD_NOT_ALLOWED, "", adapter);
        }
    }

    private void getAll(HttpExchangeAdapter adapter) throws IOException {
        QuestionListResponse response = questionService.getList();
        try {
            responseSenderService.sendOk(MarshallingService.marshall(response), adapter);
        } catch (Exception e) {
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
        }
    }

    private void findByCriteria(HttpExchangeAdapter adapter) throws IOException {
        Map<String, String> query = adapter.extractQuery();
        if (query.containsKey(AllowedQueryParams.FIND_BY_LEVEL.getQueryParam())
                || query.containsKey(AllowedQueryParams.FIND_BY_MULTIPLE_CHOICE.getQueryParam())
                || query.containsKey(AllowedQueryParams.FIND_BY_ID.getQueryParam())) {
            try {
                QuestionFindRequest criteria = getCriteriaParamsRequest(query);
                QuestionListResponse questionsList = questionService.getListByCriteria(criteria);
                responseSenderService.sendOk(MarshallingService.marshall(questionsList), adapter);
            } catch (Exception e) {
                responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
            }
        } else {
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
        }
    }

    private void save(HttpExchangeAdapter adapter) throws IOException {
        String requestBody = getRequestBody(adapter);
        if (requestBody.isEmpty())
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);

        try {
            QuestionSaveRequest saveRequest = MarshallingService.unmarshall(requestBody, new Question(), new QuestionSaveRequest());
            Question savedQuestion = questionService.save(saveRequest);
            responseSenderService.sendOk(MarshallingService.marshall(savedQuestion), adapter);
        } catch (Exception e) {
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
        }
    }

    private void delete(HttpExchangeAdapter adapter) throws IOException {
        Map<String, String> deleteQuery = adapter.extractQuery();
        String questionId = deleteQuery.get(AllowedQueryParams.FIND_BY_ID.getQueryParam());
        if (questionId != null) {
            try {
                questionService.delete(UUID.fromString(questionId));
                responseSenderService.sendVoidOK(adapter);
            } catch (Exception e) {
                responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
            }
        } else {
            responseSenderService.sendError(HttpStatus.METHOD_NOT_ALLOWED, "", adapter);
        }
    }

    private QuestionFindRequest getCriteriaParamsRequest(Map<String, String> query) {
        QuestionFindRequest criteria = new QuestionFindRequest();
        if (query.containsKey(AllowedQueryParams.FIND_BY_ID.getQueryParam()))
            criteria.setId(UUID.fromString(query.get(AllowedQueryParams.FIND_BY_ID.getQueryParam())));
        if (query.containsKey(AllowedQueryParams.FIND_BY_LEVEL.getQueryParam()))
            criteria.setLevel(DifficultyLevel.fromValue(query.get(AllowedQueryParams.FIND_BY_LEVEL.getQueryParam())));
        if (query.containsKey(AllowedQueryParams.FIND_BY_MULTIPLE_CHOICE.getQueryParam()))
            criteria.setMultipleChoice(getBoolean(query.get(AllowedQueryParams.FIND_BY_MULTIPLE_CHOICE.getQueryParam())));
        return criteria;
    }

}