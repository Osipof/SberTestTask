package sbp.sberuniversity.jcore.draft.api.controller;

import sbp.sberuniversity.jcore.draft.core.adapters.HttpExchangeAdapter;
import sbp.sberuniversity.jcore.draft.core.routers.PathSelectionService;

public final class ControllersFactory {

    private final PathSelectionService pathSelectionService;
    private final QuestionController questionController;
    private final DeadEndController deadEndController;

    public ControllersFactory(PathSelectionService pathSelectionService,
                              QuestionController questionController,
                              DeadEndController deadEndController) {
        this.pathSelectionService = pathSelectionService;
        this.questionController = questionController;
        this.deadEndController = deadEndController;
    }

    public Controller get(HttpExchangeAdapter adapter) {
        return pathSelectionService
                .get(adapter)
                .map(controllerScope -> {
                    Controller controller;
                    switch (controllerScope.getController()) {
                        case QUESTION: controller = questionController; break;
                        default: controller = deadEndController; break;
                    }
                    return controller;
                })
                .orElse(deadEndController);
    }

}
