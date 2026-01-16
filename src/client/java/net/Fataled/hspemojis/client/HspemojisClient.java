package net.Fataled.hspemojis.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HspemojisClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("HSPEmojis");

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> {
            Commands.registerCommands();
            CustomSounds.initialize();
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            AnimatedEmojis.onClientTick();
        });

    }


}
