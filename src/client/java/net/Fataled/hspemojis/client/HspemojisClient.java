package net.Fataled.hspemojis.client;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HspemojisClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("HSPEmojis");

    @Override
    public void onInitializeClient() {
        CustomSounds.initialize();
    }

}
