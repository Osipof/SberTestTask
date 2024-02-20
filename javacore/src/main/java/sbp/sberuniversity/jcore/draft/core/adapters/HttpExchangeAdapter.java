package sbp.sberuniversity.jcore.draft.core.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public interface HttpExchangeAdapter {

    String getRequestMethod();

    String getQueryParams();

    URI getRequestURI();

    Map<String, List<String>> getRequestHeaders();

    Map<String, List<String>> getResponseHeaders();

    InputStream getRequestBody();

    OutputStream getResponseBody();

    String extractMethodName();

    Map<String, String> extractQuery();

    void setResponse(int responseCode, long responseLen)throws IOException;

    void close();

}
