package sbp.sberuniversity.jcore.draft.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public final class QuestionSaveRequest extends Question { }
