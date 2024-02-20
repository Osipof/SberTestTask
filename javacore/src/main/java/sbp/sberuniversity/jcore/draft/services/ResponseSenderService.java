package sbp.sberuniversity.jcore.draft.services;

import sbp.sberuniversity.jcore.draft.api.constants.HttpStatus;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;

import java.io.IOException;

public interface ResponseSenderService {

    void sendOk(String msg, HttpExchangeAdapter adapter) throws IOException;

    void sendVoidOK(HttpExchangeAdapter adapter) throws IOException;

    void sendError(HttpStatus httpStatus, String msg, HttpExchangeAdapter adapter) throws IOException;

}
