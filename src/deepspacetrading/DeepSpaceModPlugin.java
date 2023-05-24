package deepspacetrading;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import deepspacetrading.util.DeepSpaceTariffUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class DeepSpaceModPlugin extends BaseModPlugin {

    private static final Logger log = Global.getLogger(DeepSpaceModPlugin.class);
    static {log.setLevel(Level.ALL);}

    public static final String ID = "deep_space_trading";

    @Override
    public void onGameLoad(boolean newGame) {
        new DeepSpaceTariffUtil();
        log.info("DeepSpaceTrading eventListener is loaded.");
    }
}
