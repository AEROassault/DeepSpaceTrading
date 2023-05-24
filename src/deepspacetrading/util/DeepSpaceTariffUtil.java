package deepspacetrading.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BaseCampaignEventListener;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import deepspacetrading.DeepSpaceModPlugin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DeepSpaceTariffUtil extends BaseCampaignEventListener {

    private static final Logger log = Global.getLogger(DeepSpaceTariffUtil.class);
    static {log.setLevel(Level.ALL);}

    public DeepSpaceTariffUtil() {
        super(false);
        Global.getSector().addTransientListener(this);
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {
        if (market.isHidden()) {
            market.getTariff().modifyMult(DeepSpaceModPlugin.ID, 0f);
            log.info("Modified hidden market tariff.");
        }
    }

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        if (market.isHidden()) {
            market.getTariff().unmodify(DeepSpaceModPlugin.ID);
            log.info("Unmodified hidden market tariff.");
        }
    }

}
