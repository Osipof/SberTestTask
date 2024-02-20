package sbp.sberuniversity.jcore.draft.persistence.repository.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class QuestionDAO {

    private Integer id;

    private UUID questionId;

    private LocalDateTime createdAt;

    private DifficultyLevelDAO difficultyLevel;

    private String question;

    private List<AnswerDAO> answers;

    private Boolean multipleChoice;

    public QuestionDAO(Integer id, UUID questionId, LocalDateTime createdAt, DifficultyLevelDAO difficultyLevelDAO, String question, List<AnswerDAO> answers, Boolean multipleChoice) {
        this.id = id;
        this.questionId = questionId;
        this.createdAt = createdAt;
        this.difficultyLevel = difficultyLevelDAO;
        this.question = question;
        this.answers = answers;
        this.multipleChoice = multipleChoice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DifficultyLevelDAO getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevelDAO difficultyLevelDAO) {
        this.difficultyLevel = difficultyLevelDAO;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDAO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDAO> answers) {
        this.answers = answers;
    }

    public Boolean getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(Boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDAO that = (QuestionDAO) o;
        return Objects.equals(id, that.id) && Objects.equals(questionId, that.questionId) && Objects.equals(createdAt, that.createdAt) && difficultyLevel == that.difficultyLevel && Objects.equals(question, that.question) && Objects.equals(answers, that.answers) && Objects.equals(multipleChoice, that.multipleChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, createdAt, difficultyLevel, question, answers, multipleChoice);
    }

}
