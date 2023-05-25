package deepspacetrading;

import lunalib.lunaSettings.LunaSettings;
import lunalib.lunaSettings.LunaSettingsListener;

import static deepspacetrading.DeepSpaceModPlugin.lunaLibEnabled;

public class LunaSettingsChangedListener implements LunaSettingsListener {
    @Override
    public void settingsChanged(String idOfModWithChangedSettings) {
        if (idOfModWithChangedSettings.equals(DeepSpaceModPlugin.ID)) {
            DeepSpaceModPlugin.readSettings();
        }
    }

    public static void addToManagerIfNeeded() {
        if(lunaLibEnabled && !LunaSettings.hasSettingsListenerOfClass(LunaSettingsChangedListener.class)) {
            LunaSettings.addSettingsListener(new LunaSettingsChangedListener());
        }
    }
}