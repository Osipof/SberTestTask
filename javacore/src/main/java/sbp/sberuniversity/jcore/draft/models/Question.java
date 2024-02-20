package sbp.sberuniversity.jcore.draft.models;

import lombok.Data;
import sbp.sberuniversity.jcore.draft.api.adapters.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
        "id", "createdAt", "level", "question", "answers", "multipleChoice"
})
@XmlRootElement(name = "question")
@Data
public class Question {

    protected UUID id;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    protected LocalDateTime createdAt;

    protected DifficultyLevel level;

    protected String question;

    @XmlElement(name = "answer")
    protected List<Answer> answers;

    protected Boolean multipleChoice;

}
