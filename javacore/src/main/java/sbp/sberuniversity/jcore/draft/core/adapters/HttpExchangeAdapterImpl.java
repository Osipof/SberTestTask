package sbp.sberuniversity.jcore.draft.core.adapters;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class HttpExchangeAdapterImpl implements HttpExchangeAdapter {

    private final HttpExchange httpExchange;

    public HttpExchangeAdapterImpl(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public String getRequestMethod() {
        return httpExchange.getRequestMethod();
    }

    public String getQueryParams() { return httpExchange.getRequestURI().getQuery(); }

    public URI getRequestURI() {
        return httpExchange.getRequestURI();
    }

    public Map<String, List<String>> getRequestHeaders() {
        return httpExchange.getRequestHeaders();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return httpExchange.getResponseHeaders();
    }

    public InputStream getRequestBody() {
        return httpExchange.getRequestBody();
    }

    public OutputStream getResponseBody(){
        return httpExchange.getResponseBody();
    }

    public Map<String, String> extractQuery() {
        final String query = getQueryParams();

        if (query == null || query.isEmpty())
            return Collections.emptyMap();

        List<String> pairParams = Stream.of(query.split("&"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());

        for (String elem : pairParams) {
            String[] res = elem.split("=");
            if (res.length % 2 != 0)
                return Collections.emptyMap();
        }

        return pairParams
                .stream()
                .map(kv -> kv.split("=", 2))
                .collect(Collectors.toMap(elem -> elem[0], elem -> elem[1]));
    }

    public String extractMethodName() {
        final String query = httpExchange.getRequestURI().getPath();
        if (query == null || query.isEmpty())
            return null;

        List<String> paths =  Stream.of(query.split("/"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        return paths.get(paths.size()-1);
    }

    public void setResponse(int responseCode, long responseLen) throws IOException {
        httpExchange.sendResponseHeaders(responseCode, responseLen);
    }

    public void close() {
        httpExchange.close();
    }

}
