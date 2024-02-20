package sbp.sberuniversity.jcore.draft.host.config;

import sbp.sberuniversity.jcore.draft.core.exceptions.IncorrectConfigException;

import java.util.ResourceBundle;
import java.util.Set;

public final class ConfigService {

    private static volatile Config CONFIG;

    private ConfigService() {}

    public static Config get() throws IncorrectConfigException {
        if (CONFIG == null) {
            synchronized (Config.class) {
                if (CONFIG == null) {
                    CONFIG = new Config();
                    init();
                }
            }
        }
        return CONFIG;
    }

    private static void init() throws IncorrectConfigException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("JCDraft");
            Set<String> keys = rb.keySet();

            for (String keyElem : keys) {
                if (keyElem.equals("port")) {
                    CONFIG.setPort(Integer.valueOf(rb.getString(keyElem)));
                    break;
                }
            }
        } catch (Exception ex) {
            throw new IncorrectConfigException();
        }
    }

}
