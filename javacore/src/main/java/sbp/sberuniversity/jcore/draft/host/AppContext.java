package sbp.sberuniversity.jcore.draft.host;

import com.sun.net.httpserver.HttpServer;
import sbp.sberuniversity.jcore.draft.api.constants.AllowedMethods;
import sbp.sberuniversity.jcore.draft.api.controller.ControllersFactory;
import sbp.sberuniversity.jcore.draft.core.actions.ControllerScope;
import sbp.sberuniversity.jcore.draft.core.constants.ControllerScopeElement;
import sbp.sberuniversity.jcore.draft.core.di.DIContainer;
import sbp.sberuniversity.jcore.draft.core.routers.PathSelectionService;
import sbp.sberuniversity.jcore.draft.core.routers.PathSelectionServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.AbstractMap;
import java.util.Map;

public final class AppContext {

    private final Map<String, ControllerScope> routes;

    public AppContext() {
        routes = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("GET/api/v1/question/" + AllowedMethods.LIST.getMethodName(),
                        new ControllerScope(ControllerScopeElement.QUESTION)),
                new AbstractMap.SimpleEntry<>("GET/api/v1/question/" + AllowedMethods.FIND_BY_CRITERIA.getMethodName(),
                        new ControllerScope(ControllerScopeElement.QUESTION)),
                new AbstractMap.SimpleEntry<>("PUT/api/v1/question/" + AllowedMethods.SAVE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.QUESTION)),
                new AbstractMap.SimpleEntry<>("DELETE/api/v1/question/" + AllowedMethods.DELETE.getMethodName(),
                        new ControllerScope(ControllerScopeElement.QUESTION))
        );
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        PathSelectionService router = new PathSelectionServiceImpl(routes);
        server.createContext("/", new HttpHandlerMediator(
                new ControllersFactory(
                        router,
                        DIContainer.getQuestionController(),
                        DIContainer.getDeadEndController())));
        server.setExecutor(null);
        server.start();
    }

}
