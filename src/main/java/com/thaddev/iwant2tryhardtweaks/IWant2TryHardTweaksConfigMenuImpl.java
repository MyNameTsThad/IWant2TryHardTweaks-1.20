package com.thaddev.iwant2tryhardtweaks;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;

public class IWant2TryHardTweaksConfigMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, IWant2TryHardTweaks.MODID);
    }
}
