package sbp.sberuniversity.jcore.draft.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
        "answer", "correct"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Answer {

    private String answer;

    private Boolean correct;

}
