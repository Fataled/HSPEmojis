package net.Fataled.hspemojis.mixin.client;

import net.Fataled.hspemojis.client.EmojiParser;
import net.Fataled.hspemojis.client.EmojiRegistry;
import net.Fataled.hspemojis.client.TextEmojiRewriter;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

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
        if (!EmojiRegistry.containsAnyToken(message.getString())) return message;
        return TextEmojiRewriter.rewrite(message);
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
        if (!EmojiRegistry.containsAnyToken(s)) return message;
        return EmojiParser.parse(s);
    }


}
