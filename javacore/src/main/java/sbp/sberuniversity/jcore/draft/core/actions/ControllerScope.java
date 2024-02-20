package sbp.sberuniversity.jcore.draft.core.actions;

import sbp.sberuniversity.jcore.draft.core.constants.ControllerScopeElement;

public final class ControllerScope {

    private final ControllerScopeElement controllerScopeElement;

    public ControllerScope(ControllerScopeElement controllerScopeElement) {
        this.controllerScopeElement = controllerScopeElement;
    }

    public ControllerScopeElement getController() {
        return controllerScopeElement;
    }

}
