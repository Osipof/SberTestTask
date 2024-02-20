package sbp.sberuniversity.jcore.draft.persistence.repository;

import sbp.sberuniversity.jcore.draft.persistence.repository.dao.DifficultyLevelDAO;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.QuestionDAO;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {

    List<QuestionDAO> getAll();

    List<QuestionDAO> findById(UUID questionId);

    List<QuestionDAO> findByLevel(DifficultyLevelDAO difficultyLevelDAO);

    List<QuestionDAO> findByMultipleChoice(Boolean multipleChoice);

    QuestionDAO save(QuestionDAO questionDAO);

    QuestionDAO update(QuestionDAO questionDAO);

    void delete(UUID questionId);

}
