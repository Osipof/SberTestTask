package sbp.sberuniversity.jcore.draft.persistence.repository;

import sbp.sberuniversity.jcore.draft.persistence.repository.dao.AnswerDAO;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.DifficultyLevelDAO;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.QuestionDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public final class QuestionRepositoryImpl implements QuestionRepository {

    private static int ID_SEQUENCE;
    private static final List<QuestionDAO> QUESTION_REPOSITORY;

    static {
        QUESTION_REPOSITORY = new ArrayList<>(List.of(
                new QuestionDAO(getId(), UUID.fromString("78287df9-4c8b-4716-b36b-0d041f53408a"), LocalDateTime.now(), DifficultyLevelDAO.EASY,
                        "Интерфейс расширяет интерфейс?",
                        List.of(
                                new AnswerDAO("Да", Boolean.TRUE),
                                new AnswerDAO("Нет", Boolean.FALSE)),
                        Boolean.FALSE
                ),
                new QuestionDAO(getId(), UUID.fromString("7ced0504-71a6-43e7-88d9-c8143143d2fc"), LocalDateTime.now(), DifficultyLevelDAO.EASY,
                        "Класс реализует интерфейс?",
                        List.of(
                                new AnswerDAO("Нет", Boolean.FALSE),
                                new AnswerDAO("Да", Boolean.TRUE)),
                        Boolean.FALSE
                ),
                new QuestionDAO(getId(), UUID.fromString("1ecb71e6-3230-4cad-9f16-1c06d3cb7fc2"), LocalDateTime.now(), DifficultyLevelDAO.MEDIUM,
                        "Допустима ли множественная реализация интерфейсов?",
                        List.of(
                                new AnswerDAO("Да", Boolean.TRUE),
                                new AnswerDAO("Нет", Boolean.FALSE)),
                        Boolean.FALSE
                ),
                new QuestionDAO(getId(), UUID.fromString("b15cbff0-91e9-4b13-80f0-4710b6321262"), LocalDateTime.now(), DifficultyLevelDAO.HARD,
                        "Какими методами обладает класс Object?",
                        List.of(
                                new AnswerDAO("int hashCode()", Boolean.TRUE),
                                new AnswerDAO("boolean equals(Object obj)", Boolean.TRUE),
                                new AnswerDAO("int compareTo(CharValue obj)", Boolean.FALSE),
                                new AnswerDAO("int compareToIgnoreCase(Object obj)", Boolean.FALSE),
                                new AnswerDAO("Object concat(Object obj)", Boolean.FALSE)),
                        Boolean.TRUE
                ),
                new QuestionDAO(getId(), UUID.fromString("43a2aa22-62bf-4a00-87a9-456c21b05c80"), LocalDateTime.now(), DifficultyLevelDAO.HARD,
                        "Допустима ли запись: int f = 11L;",
                        List.of(
                                new AnswerDAO("Нет", Boolean.TRUE),
                                new AnswerDAO("Да", Boolean.FALSE)),
                        Boolean.FALSE
                ),
                new QuestionDAO(getId(), UUID.fromString("2f60932c-71f0-4acb-ba64-fdc6529f1699"), LocalDateTime.now(), DifficultyLevelDAO.HARD,
                        "Допустима ли запись: Float f = 12.42;",
                        List.of(
                                new AnswerDAO("Нет", Boolean.TRUE),
                                new AnswerDAO("Да", Boolean.FALSE)),
                        Boolean.FALSE
                )
        ));
    }

    public List<QuestionDAO> getAll() {
        return QUESTION_REPOSITORY;
    }

    public Optional<QuestionDAO> getById(UUID questionId) {
        for (QuestionDAO elem : QUESTION_REPOSITORY) {
            if (elem.getQuestionId().equals(questionId))
                return Optional.of(elem);
        }
        return Optional.empty();
    }

    @Override
    public List<QuestionDAO> findById(UUID questionId) {
        return getAll().stream()
                .filter(question -> question.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDAO> findByLevel(DifficultyLevelDAO difficultyLevelDAO) {
        return getAll().stream()
                .filter(question -> question.getDifficultyLevel().equals(difficultyLevelDAO))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDAO> findByMultipleChoice(Boolean multipleChoice) {
        return getAll().stream()
                .filter(question -> question.getMultipleChoice().equals(multipleChoice))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDAO save(QuestionDAO questionDAO) {
        questionDAO.setId(getId());
        questionDAO.setQuestionId(UUID.randomUUID());
        QUESTION_REPOSITORY.add(questionDAO);

        return QUESTION_REPOSITORY.get(QUESTION_REPOSITORY.size()-1);
    }

    @Override
    public QuestionDAO update(QuestionDAO source) {
        QuestionDAO existingQuestion = getById(source.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found!"));

        existingQuestion.setCreatedAt(source.getCreatedAt());
        existingQuestion.setDifficultyLevel(source.getDifficultyLevel());
        existingQuestion.setQuestion(source.getQuestion());
        existingQuestion.setAnswers(source.getAnswers());
        existingQuestion.setMultipleChoice(source.getMultipleChoice());
        QUESTION_REPOSITORY.set(getIdxOf(source), existingQuestion);

        return getById(source.getQuestionId())
                .get();
    }

    @Override
    public void delete(UUID questionId) {
        QuestionDAO existingRecord = getById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found!"));

        QUESTION_REPOSITORY.remove(getIdxOf(existingRecord));
    }

    private static int getId(){
        return ++ID_SEQUENCE;
    }

    private static int getIdxOf(QuestionDAO questionDAO) {
        return QUESTION_REPOSITORY.indexOf(
                QUESTION_REPOSITORY
                        .stream()
                        .filter(elem -> elem.getQuestionId().equals(questionDAO.getQuestionId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Question not found")));
    }

}
