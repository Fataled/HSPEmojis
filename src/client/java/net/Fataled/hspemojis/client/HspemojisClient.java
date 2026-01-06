package net.Fataled.hspemojis.client;

import net.Fataled.hspemojis.CustomSounds;
import net.Fataled.hspemojis.Emoji;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HspemojisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModContainer container = FabricLoader.getInstance()
                .getModContainer("hspemojis")
                .orElseThrow(() -> new IllegalStateException("Mod container not found"));

        ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of("hspemojis", "hspemojis"),
                container,
                ResourcePackActivationType.ALWAYS_ENABLED
        );

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
