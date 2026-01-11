package net.Fataled.hspemojis.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import static net.Fataled.hspemojis.client.EmojiRegistry.EMOJIS;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Commands {

    public static void registerCommands() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("HSP")
                .then(literal("Emojis")
                        .executes(ctx -> {
                            String output = EMOJIS.stream()
                                    .map(e -> e.getEmojiNameClean() + " -> " + e.getEmojiName())
                                    .collect(java.util.stream.Collectors.joining("\n"));
                            MinecraftClient.getInstance().player.sendMessage(Text.literal("List of Emojis:\n " + output), false);
                            return 1;
                        })))

        );



    }
}
