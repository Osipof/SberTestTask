package sbp.sberuniversity.jcore.draft.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
@Data
@EqualsAndHashCode(callSuper = true)
public final class QuestionSaveResponse extends Question { }
