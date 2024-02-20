package sbp.sberuniversity.jcore.draft.services;

import sbp.sberuniversity.jcore.draft.models.QuestionFindRequest;
import sbp.sberuniversity.jcore.draft.models.QuestionListResponse;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveRequest;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveResponse;

import java.util.UUID;

public interface QuestionService {

    QuestionListResponse getList();

    QuestionListResponse getListByCriteria(QuestionFindRequest criteria);

    QuestionSaveResponse save(QuestionSaveRequest request);

    void delete(UUID questionId);

}
