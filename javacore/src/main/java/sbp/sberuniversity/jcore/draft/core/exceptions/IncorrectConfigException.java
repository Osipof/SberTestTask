package sbp.sberuniversity.jcore.draft.core.exceptions;

import sbp.sberuniversity.jcore.draft.core.constants.ErrorConstants;

public final class IncorrectConfigException extends AppHostException {

    public IncorrectConfigException() {
        super(ErrorConstants.INCORRECT_CONFIG_ERROR);
    }

}
