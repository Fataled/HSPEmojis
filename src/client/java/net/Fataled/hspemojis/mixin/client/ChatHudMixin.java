package net.Fataled.hspemojis.mixin.client;

import net.Fataled.hspemojis.client.Emoji;
import net.Fataled.hspemojis.client.EmojiParser;
import net.Fataled.hspemojis.client.EmojiRegistry;
import net.Fataled.hspemojis.client.TextEmojiRewriter;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import static net.Fataled.hspemojis.client.TextEmojiRewriter.findEmojisIn;


@Mixin(ChatHud.class)
public class ChatHudMixin {

    /**
     * NOTE: This version rewrites based on message.getString().
     * That means it may drop some formatting/colors coming from the server.
     * It is the fastest “prove it works” implementation.
     */
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/text/Text;)V",
            at = @At("HEAD"),
            argsOnly = true,
            require = 0
    )
    private Text hspemojis$rewriteSimple(Text message) {
        if (EmojiRegistry.containsAnyToken(message.getString())) return message;
        Text finalOutpot = TextEmojiRewriter.rewrite(message);

        if(!shouldPlayForMessage(message.getString()) || message.toString().contains("List of Emojis:")) return finalOutpot;

        System.out.println("Checking if it found an emoji "+ findEmojisIn(message.getString()));

        for (Emoji e: findEmojisIn(message.getString())){
            e.playEmoji();
            System.out.println("[HSPEMOJI] Played a sound");
        }

    return finalOutpot;
    }

    // Some versions use a longer overload. This keeps you compatible without guessing.
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            at = @At("HEAD"),
            argsOnly = true,
            require = 0
    )
    private Text hspemojis$rewriteSigned(Text message) {
        String s = message.getString();
        if (EmojiRegistry.containsAnyToken(s)) return message;
        return EmojiParser.parse(s);
    }

    @Unique
    private static final java.util.Map<String, Long> CHAT_SOUND_DEDUPE = new java.util.HashMap<>();
    @Unique
    private static final long DEDUPE_MS = 750;

    @Unique
    private static boolean shouldPlayForMessage(String key) {
        long now = System.currentTimeMillis();
        Long last = CHAT_SOUND_DEDUPE.get(key);
        if (last != null && (now - last) < DEDUPE_MS) return false;

        CHAT_SOUND_DEDUPE.put(key, now);

        if (CHAT_SOUND_DEDUPE.size() > 300) CHAT_SOUND_DEDUPE.clear();
        return true;
    }




}
