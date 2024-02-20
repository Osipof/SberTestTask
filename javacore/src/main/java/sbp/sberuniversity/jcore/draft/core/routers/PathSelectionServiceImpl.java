package sbp.sberuniversity.jcore.draft.core.routers;

import sbp.sberuniversity.jcore.draft.core.actions.ControllerScope;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public final class PathSelectionServiceImpl implements PathSelectionService {

    private final Map<String, ControllerScope> paths;

    public PathSelectionServiceImpl(Map<String, ControllerScope> paths) {
        this.paths = paths;
    }

    @Override
    public Optional<ControllerScope> get(HttpExchangeAdapter adapter) {
        for (Map.Entry<String, ControllerScope> elem : paths.entrySet()) {
            if (Pattern.matches(elem.getKey(), buildPath(adapter)))
                return Optional.of(elem.getValue());
        }
        return Optional.empty();
    }

    private String buildPath(HttpExchangeAdapter adapter) {
        String requestMethod = adapter.getRequestMethod();
        String uriPath = adapter.getRequestURI().getPath();

        return requestMethod + uriPath;
    }

}
