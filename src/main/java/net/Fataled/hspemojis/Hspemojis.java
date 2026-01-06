package net.Fataled.hspemojis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Hspemojis implements ModInitializer {

    @Override
    public void onInitialize() {

        CustomSounds.initialize();
        List<Emoji> emojis = new ArrayList<>();
        ClientReceiveMessageEvents.GAME.register((Text message, boolean overlay) -> {
            for(Emoji emoji : emojis){
                if(message.getString().contains(emoji.getEmojiId())){
                    // replace word with emoji image and play sound if applicable
                }
                // do nuthin
            }
        });

    }
}
