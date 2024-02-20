package sbp.sberuniversity.jcore.draft.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sbp.sberuniversity.jcore.draft.models.*;
import sbp.sberuniversity.jcore.draft.persistence.models.QuestionFindCriteria;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.AnswerDAO;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.QuestionDAO;

import java.util.List;

@Mapper
public abstract class QuestionMapper {

    public abstract AnswerDAO map2AnswerDAO(Answer answer);

    public abstract List<AnswerDAO> map2AnswerDAOList(List<Answer> answers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionId", source = "id")
    @Mapping(target = "difficultyLevel", source = "level")
    public abstract QuestionDAO map2QuestionDAO(Question question);

    public abstract List<QuestionDAO> map2QuestionDAOList(List<Question> questions);

    public abstract Answer map2Answer(AnswerDAO answerDAO);

    public abstract List<Answer> map2AnswerList(List<AnswerDAO> answersDAO);

    @Mapping(target = "id", source = "questionId")
    @Mapping(target = "level", source = "difficultyLevel")
    public abstract QuestionSaveResponse map2SaveResponse(QuestionDAO questionDAO);

    public abstract List<Question> map2QuestionList(List<QuestionDAO> questionsDAO);

    public QuestionListResponse map2QuestionResponse(List<QuestionDAO> questionsDAO) {
        return new QuestionListResponse(map2QuestionList(questionsDAO));
    }

    public abstract QuestionFindCriteria map2Criteria(QuestionFindRequest request);

}
