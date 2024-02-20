package sbp.sberuniversity.jcore.draft.core.routers;

import sbp.sberuniversity.jcore.draft.core.actions.ControllerScope;
import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;

import java.util.Optional;

public interface PathSelectionService {

    Optional<ControllerScope> get(HttpExchangeAdapter adapter);

}
