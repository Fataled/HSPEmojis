package net.Fataled.hspemojis.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class HspemojisClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("HSPEmojis");

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

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            LOGGER.info("[Init] Mod registered.");
        });

        CustomSounds.initialize();

        List<Emoji> emojis = getEmojis();

        ClientReceiveMessageEvents.MODIFY_GAME.register((Text message, boolean overlay) -> {
            String raw = message.getString();
            String newMsg = raw;

            LOGGER.info(raw);

            for (Emoji emoji : emojis) {
                LOGGER.info(emoji.EmojiName);

                if (newMsg.contains(emoji.EmojiName)) {
                    LOGGER.info("Found emoji: {}", emoji.getEmojiId());

                    // Replace all occurrences of the emoji ID
                    newMsg = newMsg.replace(emoji.EmojiName, emoji.EmojiId); // TODO: replace with glyph later

                    if (emoji.CustomSound != null) {
                        emoji.playEmoji();
                    }
                }
                // NOTE: no return here
            }

            // If nothing changed, tell Fabric "no modification"
            if (newMsg.equals(raw)) {
                return Text.literal(raw);
            }

            // Otherwise return the modified text
            return Text.literal(newMsg);
                });
    }

    private static @NotNull List<Emoji> getEmojis() {
        return List.of(
        new Emoji(":meow:", "\uE000", null),
        new Emoji(":fu:", "\uE001", null),
        new Emoji(":pol:", "\uE002", null),
        new Emoji(":artempetuh:", "\uE003", null),
        new Emoji(":true:", "\uE004", null),
        new Emoji(":false:", "\uE005", null),
        new Emoji(":cordeliaheart:", "\uE006", null),
        new Emoji(":fear:", "\uE007", null),
        new Emoji(":ashbaby:", "\uE008", null),
        new Emoji(":baer", "\uE009", null),
        new Emoji(":aswhat", "\uE010", null),
        new Emoji(":donot:", "\uE011", null)
        );
    }
}
