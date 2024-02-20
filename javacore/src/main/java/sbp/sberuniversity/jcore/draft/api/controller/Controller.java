package sbp.sberuniversity.jcore.draft.api.controller;

import sbp.sberuniversity.jcore.draft.api.constants.AllowedMethods;
import sbp.sberuniversity.jcore.draft.api.constants.AllowedQueryParams;
import sbp.sberuniversity.jcore.draft.api.constants.HttpStatus;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;
import sbp.sberuniversity.jcore.draft.services.ResponseSenderService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Controller {

    protected final Map<String, Map<AllowedMethods, List<AllowedQueryParams>>> supportedRoutes;
    protected final ResponseSenderService responseSenderService;

    protected Controller(Map<String, Map<AllowedMethods, List<AllowedQueryParams>>> supportedRoutes,
                         ResponseSenderService responseSenderService) {
        this.supportedRoutes = supportedRoutes;
        this.responseSenderService = responseSenderService;
    }

    public final void handleRequest(HttpExchangeAdapter adapter) throws IOException {
        checkRequestMediaType(adapter);
        final Map<AllowedMethods, List<AllowedQueryParams>> supportedMethods = supportedRoutes.get(adapter.getRequestMethod());
        final String requestedMethod = adapter.extractMethodName().toLowerCase(Locale.ROOT);

        if (supportedMethods != null && isMethodSupported(supportedMethods, requestedMethod)) {
            invokeControllerMethod(adapter, AllowedMethods.getEnum(requestedMethod));
        } else {
            responseSenderService.sendError(HttpStatus.METHOD_NOT_ALLOWED, "", adapter);
        }
    }

    public abstract void invokeControllerMethod(HttpExchangeAdapter adapter, AllowedMethods methodToInvoke) throws IOException;

    private void checkRequestMediaType(HttpExchangeAdapter adapter) throws IOException {
        List<String> contentType = adapter.getRequestHeaders().get("Content-Type");
        if (contentType == null || !contentType.contains("application/xml")) {
            responseSenderService.sendError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "", adapter);
        }
    }

    private boolean isMethodSupported(Map<AllowedMethods, List<AllowedQueryParams>> supportedMethod, String requestedMethod) {
        AllowedMethods allowedMethods = AllowedMethods.getEnum(requestedMethod);
        return supportedMethod.containsKey(allowedMethods);
    }

    protected String getRequestBody(HttpExchangeAdapter adapter) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(adapter.getRequestBody())){
            byte[] buff = new byte[1024 * 4];
            int bytesRead;
            while((bytesRead = bis.read(buff)) != -1) {
                String payloadChunk = new String(buff, 0, bytesRead);
                sb.append(payloadChunk);
            }
        } catch (IOException ioEx) {
            responseSenderService.sendError(HttpStatus.INTERNAL_SERVER_ERROR, "", adapter);
        }
        return sb.toString();
    }

    protected void checkQueryParamsPairing(HttpExchangeAdapter adapter) throws IOException {
        Map<String, String> queryParams = adapter.extractQuery();
        if (queryParams.isEmpty()) {
            responseSenderService.sendError(HttpStatus.BAD_REQUEST, "", adapter);
        }
    }

    protected boolean getBoolean(String booleanQueryParam) {
        if ("true".equalsIgnoreCase(booleanQueryParam)) {
            return true;
        } else if ("false".equalsIgnoreCase(booleanQueryParam)) {
            return false;
        } else {
            throw new IllegalArgumentException("Incorrect boolean value!");
        }
    }

}