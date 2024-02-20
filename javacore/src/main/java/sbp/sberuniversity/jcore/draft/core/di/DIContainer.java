package sbp.sberuniversity.jcore.draft.core.di;

import sbp.sberuniversity.jcore.draft.api.constants.QuestionControllerRoutes;
import sbp.sberuniversity.jcore.draft.api.controller.DeadEndController;
import sbp.sberuniversity.jcore.draft.api.controller.QuestionController;
import sbp.sberuniversity.jcore.draft.persistence.repository.QuestionRepository;
import sbp.sberuniversity.jcore.draft.persistence.repository.QuestionRepositoryImpl;
import sbp.sberuniversity.jcore.draft.services.QuestionService;
import sbp.sberuniversity.jcore.draft.services.QuestionServiceImpl;
import sbp.sberuniversity.jcore.draft.services.ResponseSenderService;
import sbp.sberuniversity.jcore.draft.services.ResponseSenderServiceImpl;
import sbp.sberuniversity.jcore.draft.services.mappers.QuestionMapper;
import sbp.sberuniversity.jcore.draft.services.mappers.QuestionMapperImpl;

import java.util.Collections;

public final class DIContainer {

    private static ResponseSenderService responseSenderService;

    private static QuestionRepository questionRepository;

    private static QuestionMapper questionMapper;

    private static QuestionService questionService;

    private static QuestionController questionController;

    private static DeadEndController deadEndController;

    public static QuestionController getQuestionController() {
        if (questionController == null)
            questionController = new QuestionController(QuestionControllerRoutes.SUPPORTED_ROUTES, getResponseSenderService(), getQuestionService());
        return questionController;
    }

    public static DeadEndController getDeadEndController() {
        if (deadEndController == null)
            deadEndController = new DeadEndController(Collections.emptyMap(), getResponseSenderService());
        return deadEndController;
    }

    private static ResponseSenderService getResponseSenderService() {
        if (responseSenderService == null)
            responseSenderService = new ResponseSenderServiceImpl();
        return responseSenderService;
    }

    private static QuestionRepository getQuestionRepository() {
        if (questionRepository == null)
            questionRepository = new QuestionRepositoryImpl();
        return questionRepository;
    }

    private static QuestionMapper getQuestionMapper(){
        if (questionMapper == null)
            questionMapper = new QuestionMapperImpl();
        return questionMapper;
    }

    private static QuestionService getQuestionService() {
        if (questionService == null)
            questionService = new QuestionServiceImpl(getQuestionRepository(), getQuestionMapper());
        return questionService;
    }

}
