package sbp.sberuniversity.jcore.draft.api.controller;

import sbp.sberuniversity.jcore.draft.api.constants.AllowedMethods;
import sbp.sberuniversity.jcore.draft.api.constants.AllowedQueryParams;
import sbp.sberuniversity.jcore.draft.api.constants.HttpStatus;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;
import sbp.sberuniversity.jcore.draft.services.ResponseSenderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class DeadEndController extends Controller {

    public DeadEndController(Map<String, Map<AllowedMethods, List<AllowedQueryParams>>> allowedRoutes,
                             ResponseSenderService responseSenderService) {
        super(allowedRoutes, responseSenderService);
    }

    @Override
    public void invokeControllerMethod(HttpExchangeAdapter adapter, AllowedMethods methodToInvoke) throws IOException {
        responseSenderService.sendError(HttpStatus.FORBIDDEN, "", adapter);
    }

}
