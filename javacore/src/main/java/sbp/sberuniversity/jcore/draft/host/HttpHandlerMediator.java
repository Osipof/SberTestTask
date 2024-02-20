package sbp.sberuniversity.jcore.draft.host;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sbp.sberuniversity.jcore.draft.api.controller.Controller;
import sbp.sberuniversity.jcore.draft.api.controller.ControllersFactory;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapterImpl;

import java.io.IOException;

public final class HttpHandlerMediator implements HttpHandler {

    private final ControllersFactory controllersFactory;

    public HttpHandlerMediator(ControllersFactory controllersFactory) {
        this.controllersFactory = controllersFactory;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        HttpExchangeAdapter adapter = new HttpExchangeAdapterImpl(httpExchange);
        Controller controller = controllersFactory.get(adapter);
        controller.handleRequest(adapter);
    }

}
