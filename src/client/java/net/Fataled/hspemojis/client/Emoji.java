package net.Fataled.hspemojis.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;

public class Emoji {
    public String EmojiName;
    public String EmojiId;
    public SoundEvent CustomSound;

    public Emoji(String EmojiName, String EmojiId,  SoundEvent CustomSoundId) {
        this.EmojiName = EmojiName;
        this.EmojiId = EmojiId;
        this.CustomSound = CustomSoundId;
    }

    public String getEmojiId(){
        return EmojiId;
    }

    public void playEmoji() {
        //Here I need to figure out how and why I dont get getInstance() but after we play sound based n the player and have to isolate it to only the player
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.world == null){return;}

        if (this.CustomSound == null){return;}

        mc.player.playSound(this.CustomSound, 1.0f, 1.0f);

    }

    public String getEmojiName(){return EmojiName;}




    // Playing sounds seems to come from the world so im guessing MAYBE I need to create a new play Sound handler
}
