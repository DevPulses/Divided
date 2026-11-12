package com.divided;

import com.divided.registry.ModBlocks;
import com.divided.registry.ModEntities;
import com.divided.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DividedMod implements ModInitializer {
    public static final String MOD_ID = "divided";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Divided Mod — The Path to Insanity!");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModEntities.registerModEntities();
    }
}
