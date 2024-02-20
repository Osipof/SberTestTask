package sbp.sberuniversity.jcore.draft.persistence.repository.dao;

public final class AnswerDAO {

    private String answer;

    private Boolean correct;

    public AnswerDAO(String answer, Boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}
