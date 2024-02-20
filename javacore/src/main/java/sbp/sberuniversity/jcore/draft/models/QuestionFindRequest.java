package sbp.sberuniversity.jcore.draft.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public final class QuestionFindRequest {

    private UUID id;

    private DifficultyLevel level;

    private Boolean multipleChoice;

}
