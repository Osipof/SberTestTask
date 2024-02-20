package sbp.sberuniversity.jcore.draft.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.DifficultyLevelDAO;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class QuestionFindCriteria {

    private UUID id;

    private DifficultyLevelDAO level;

    private Boolean multipleChoice;

}
