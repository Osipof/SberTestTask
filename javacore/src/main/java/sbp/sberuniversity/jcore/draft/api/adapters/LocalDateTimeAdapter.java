package sbp.sberuniversity.jcore.draft.api.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public LocalDateTime unmarshal(String ld) {
        return LocalDateTime.parse(ld, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDateTime ld) {
        return ld.format(DATE_TIME_FORMATTER);
    }

}
