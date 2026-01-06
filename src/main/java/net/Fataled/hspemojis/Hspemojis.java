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
        Emoji Meow = new Emoji("Meow", ":meow:", null);
        List<Emoji> emojis = new ArrayList<>();
        emojis.add(Meow);

        ClientReceiveMessageEvents.MODIFY_GAME.register((Text message, boolean overlay) -> {
            for(Emoji emoji : emojis){
                if(message.getString().contains(emoji.getEmojiId())){
                    String raw = message.getString();
                    String newMsg = raw.replaceAll(emoji.getEmojiId(), "");//TODO change this once we make the texture pack for it
                    if (emoji.CustomSound != null){
                        emoji.playEmoji();
                    }
                    return Text.literal(newMsg);
                    // replace word with emoji image and play sound if applicable
                }
                return null;
                // do nuthin
            }
        return null;
        });

    }
}
