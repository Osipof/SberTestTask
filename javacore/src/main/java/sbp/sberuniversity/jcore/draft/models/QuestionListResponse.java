package sbp.sberuniversity.jcore.draft.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "questions", propOrder = {
        "questions"
})
@XmlRootElement(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
public final class QuestionListResponse {

    @XmlElement(name = "question")
    private List<Question> questions;

}
