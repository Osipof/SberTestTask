package sbp.sberuniversity.jcore.draft.services;

import sbp.sberuniversity.jcore.draft.models.Question;
import sbp.sberuniversity.jcore.draft.models.QuestionListResponse;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public final class MarshallingService {

    public static String marshall(QuestionListResponse questionListResponse) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(questionListResponse.getClass());
        Marshaller marshaller = getMarshaller(context);

        StringWriter writer = new StringWriter();
        marshaller.marshal(questionListResponse, writer);

        return writer.toString();
    }

    public static String marshall(Question question) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(question.getClass());
        Marshaller marshaller = getMarshaller(context);

        StringWriter writer = new StringWriter();
        marshaller.marshal(question, writer);

        return writer.toString();
    }

    private static Marshaller getMarshaller(JAXBContext context) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    public static QuestionSaveRequest unmarshall(String payload, Question parent, QuestionSaveRequest child) throws JAXBException {
        Class<? extends Question> anParent = parent.getClass();
        Class<? extends QuestionSaveRequest> anChild = child.getClass();
        Unmarshaller unmarshaller = JAXBContext.newInstance(anParent, anChild).createUnmarshaller();

        Object obj = unmarshaller.unmarshal(new StringReader(payload));
        if (!anChild.isInstance(obj)) {
            throw new JAXBException("Cannot unmarshall data");
        }
        return anChild.cast(obj);
    }

}
