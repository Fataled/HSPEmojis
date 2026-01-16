package net.Fataled.hspemojis.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;

public class Emoji {
    public String EmojiName;
    public SoundEvent CustomSound;
    public int BaseCodePoint;

    public Emoji(String EmojiName, SoundEvent CustomSoundId, int BaseCodePoint) {
        this.EmojiName = EmojiName;
        this.CustomSound = CustomSoundId;
        this.BaseCodePoint = BaseCodePoint;
    }

    public void playEmoji() {
        //Here I need to figure out how and why I dont get getInstance() but after we play sound based n the player and have to isolate it to only the player
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.world == null){return;}

        if (this.CustomSound == null){return;}

        assert mc.player != null;
        mc.player.playSound(this.CustomSound, 1.0f, 1.0f);

    }

    public String getEmojiName(){return EmojiName;}

    public String getEmojiNameClean(){return this.EmojiName.replace(":", "");}

    public boolean isAnimated(){return false;}

    public int getRenderCodePoint(int tick){
        return BaseCodePoint;
    }

    public char asChar(){
        return (char) BaseCodePoint;
    }

    public String asString(){
        return new String(Character.toChars(BaseCodePoint));
    }
    // Playing sounds seems to come from the world so im guessing MAYBE I need to create a new play Sound handler
}
