package net.Fataled.hspemojis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

public class Hspemojis implements ModInitializer {

    @Override
    public void onInitialize() {
        ClientReceiveMessageEvents.GAME.register((Text message, boolean overlay) -> {
            for(String ids :Emoji){
                // do nuthin
            }
        });

    }
}
