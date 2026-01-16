package net.Fataled.hspemojis.client;

import net.minecraft.sound.SoundEvent;

import java.util.HashMap;
import java.util.Map;

public class AnimatedEmojis extends Emoji{

    private final int[] frames;
    private final int frameDurationTicks;
    Map<Integer, int[]> AnimatedEmojiFrames = new HashMap<>();

    public AnimatedEmojis(String EmojiName, SoundEvent CustomSound, int BaseCodePoint, int[] frames, int frameDurationTicks){
        super(EmojiName, CustomSound, BaseCodePoint);
        this.frames = frames;
        this.frameDurationTicks = frameDurationTicks;

    }

    @Override
    public boolean isAnimated(){
        return true;
    }

    @Override
    public int getRenderCodePoint(int tick) {
        if (frames == null || frames.length == 0) {
            return BaseCodePoint;
        }
        int frameIndex = (tick / frameDurationTicks) % frames.length;
        return frames[frameIndex];
    }

    private void EmojiFrameConnection(){
        AnimatedEmojiFrames.put(0xE918, new int[]{0xE919, 0xE91A});
    }

}
