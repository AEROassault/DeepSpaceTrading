package deepspacetrading;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import deepspacetrading.util.DeepSpaceTariffUtil;
import lunalib.lunaSettings.LunaSettings;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;

import static deepspacetrading.LunaSettingsChangedListener.addToManagerIfNeeded;


public class DeepSpaceModPlugin extends BaseModPlugin {

    private static final Logger log = Global.getLogger(DeepSpaceModPlugin.class);
    static {log.setLevel(Level.ALL);}

    public static final String ID = "deep_space_trading";
    public static final String MOD_PREFIX = "deepspace_";
    public static boolean lunaLibEnabled = Global.getSettings().getModManager().isModEnabled("lunalib");

    static <T> T get(String id, Class<T> type) throws Exception {
        if (lunaLibEnabled) {
            if (type == Boolean.class) return type.cast(LunaSettings.getBoolean(DeepSpaceModPlugin.ID, MOD_PREFIX + id));
            if (type == Integer.class) return type.cast(LunaSettings.getInt(DeepSpaceModPlugin.ID, MOD_PREFIX + id));

        } else {
            if (type == Boolean.class) return type.cast(Global.getSettings().getBoolean(id));
            if (type == Integer.class) return type.cast(Global.getSettings().getInt(id));

        }
        throw new MissingResourceException("No setting found with id: " + id, type.getName(), id);
    }
    static boolean getBoolean(String id) throws Exception { return get(id, Boolean.class); }
    static int getInt(String id) throws Exception { return get(id, Integer.class); }

    static void readSettings() {
        try {
            EnableTariffModifier = getBoolean("EnableTariffModifier");
            TariffMultiplier = getInt("TariffMultiplier");
        } catch (Exception e) {
            log.debug("Failed to read LunaSettings. Exception: " + e);
        }
    }

    public static boolean EnableTariffModifier = true;
    public static int TariffMultiplier = 0;

    @Override
    public void onGameLoad(boolean newGame) {
        new DeepSpaceTariffUtil();
        log.info("DeepSpaceTrading eventListener is loaded.");
        readSettings();
    }

    @Override
    public void onApplicationLoad() throws Exception {
        if (lunaLibEnabled){
            addToManagerIfNeeded();
        }
    }
}
