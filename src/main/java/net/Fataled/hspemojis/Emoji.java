package net.Fataled.hspemojis;

import net.minecraft.sound.SoundEvent;
import java.util.ArrayList;
import java.util.List;

public class Emoji {
    public String EmojiName;
    public String EmojiId;
    public SoundEvent customSounds;

    public Emoji(String EmojiName, String EmojiId,  String  CustomSoundId) {
        this.EmojiName = EmojiName;
        this.EmojiId = EmojiId;
        this.customSounds = CustomSounds.registerSound(CustomSoundId);
    }

    public String getEmojiId(){
        return EmojiId;
    }

    public void playSound(SoundEvent sound) {
        //Here I need to figure out how and why I dont get getInstance() but after we play sound based n the player and have to isolate it to only the player
    }



    // Playing sounds seems to come from the world so im guessing MAYBE I need to create a new play Sound handler
}
