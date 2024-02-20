package sbp.sberuniversity.jcore.draft.services;

import sbp.sberuniversity.jcore.draft.models.QuestionFindRequest;
import sbp.sberuniversity.jcore.draft.models.QuestionListResponse;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveRequest;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveResponse;
import sbp.sberuniversity.jcore.draft.persistence.models.QuestionFindCriteria;
import sbp.sberuniversity.jcore.draft.persistence.repository.QuestionRepository;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.QuestionDAO;
import sbp.sberuniversity.jcore.draft.services.mappers.QuestionMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionListResponse getList() {
        List<QuestionDAO> questionsDAO = questionRepository.getAll();
        return questionMapper.map2QuestionResponse(questionsDAO);
    }

    @Override
    public QuestionListResponse getListByCriteria(QuestionFindRequest criteria) {
        QuestionFindCriteria mappedCriteria = questionMapper.map2Criteria(criteria);
        Set<QuestionDAO> questions = new HashSet<>();

        if (mappedCriteria.getId() != null) {
            questions.addAll(questionRepository.findById(mappedCriteria.getId()));
        }
        if (mappedCriteria.getLevel() != null) {
            questions.addAll(questionRepository.findByLevel(mappedCriteria.getLevel()));
        }
        if (mappedCriteria.getMultipleChoice() != null) {
            questions.addAll(questionRepository.findByMultipleChoice(mappedCriteria.getMultipleChoice()));
        }

        return questionMapper.map2QuestionResponse(questions.stream().toList());
    }

    @Override
    public QuestionSaveResponse save(QuestionSaveRequest request) {
        QuestionDAO mappedQuestionSaveRequest = questionMapper.map2QuestionDAO(request);
        QuestionDAO savedQuestion;

        if (mappedQuestionSaveRequest.getQuestionId() == null) {
            savedQuestion = questionRepository.save(mappedQuestionSaveRequest);
        } else {
            savedQuestion = questionRepository.update(mappedQuestionSaveRequest);
        }

        return questionMapper.map2SaveResponse(savedQuestion);
    }

    @Override
    public void delete(UUID questionId) {
        questionRepository.delete(questionId);
    }

}
