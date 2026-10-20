package com.example.scopenbreak;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScopenBreak implements ModInitializer {
    public static final String MOD_ID = "scopenbreak";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("ScopenBreak loaded. If reinforced blocks still can't be broken by non-owners, "
                + "check the log for mixin 'target not found' warnings.");
    }
}
