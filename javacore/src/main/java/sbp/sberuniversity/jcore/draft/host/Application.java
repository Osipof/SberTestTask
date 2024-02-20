package sbp.sberuniversity.jcore.draft.host;

import sbp.sberuniversity.jcore.draft.core.exceptions.IncorrectConfigException;
import sbp.sberuniversity.jcore.draft.host.config.Config;
import sbp.sberuniversity.jcore.draft.host.config.ConfigService;

import java.io.IOException;

public final class Application {

    public static void main(String[] args) throws IOException, IncorrectConfigException {
        Config config = ConfigService.get();

        new AppContext().start(config.getPort());

        System.out.println("INFO :: SYSTEM ONLINE");
        System.out.println("INFO :: LISTENING LOCALHOST:" + config.getPort());
    }

}
