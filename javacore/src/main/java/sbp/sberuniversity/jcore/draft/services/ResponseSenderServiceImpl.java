package sbp.sberuniversity.jcore.draft.services;

import sbp.sberuniversity.jcore.draft.api.constants.HttpStatus;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class ResponseSenderServiceImpl implements ResponseSenderService {

    @Override
    public void sendOk(String msg, HttpExchangeAdapter adapter) throws IOException {
        sendResponse(HttpStatus.OK.value(), msg, adapter);
    }

    @Override
    public void sendVoidOK(HttpExchangeAdapter adapter) throws IOException {
        sendResponse(HttpStatus.NO_CONTENT.value(), "", adapter);
    }

    @Override
    public void sendError(HttpStatus httpStatus, String msg, HttpExchangeAdapter adapter) throws IOException {
        sendResponse(httpStatus.value(), msg, adapter);
    }

    private void sendResponse(int status, String message, HttpExchangeAdapter adapter) throws IOException {
        adapter.getResponseHeaders().put("Content-Type", List.of("application/xml; charset=utf-8"));
        adapter.setResponse(status, (message == null || message.isEmpty()) ? -1 : getMessageBytes(message).length);

        OutputStream os = adapter.getResponseBody();
        if (message != null)
            os.write(getMessageBytes(message));
        else
            os.write(0);
        adapter.close();
    }

    private byte[] getMessageBytes(String message) {
        return message.getBytes(StandardCharsets.UTF_8);
    }

}
