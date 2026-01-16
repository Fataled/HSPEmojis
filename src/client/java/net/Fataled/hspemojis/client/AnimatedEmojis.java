package net.Fataled.hspemojis.client;

import net.minecraft.sound.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedEmojis extends Emoji {

    private final int[] frames;
    public static final int frameDurationTicks = 5;
    public static int GlobalTick = 0;
    public static Map<Integer, int[]> ANIMATED_GLOBAL_FRAMES = new HashMap<>();
    public static List<AnimatedEmojis> animatedEmojis = new ArrayList<>();

    public AnimatedEmojis(String EmojiName, SoundEvent CustomSound, int BaseCodePoint, int[] frames) {
        super(EmojiName, CustomSound, BaseCodePoint);
        this.frames = frames;

        mapEmojisToFrames();
    }

    @Override
    public boolean isAnimated() {
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

    public static void onClientTick() {
        GlobalTick++;
    }

    public void mapEmojisToFrames() {
        ANIMATED_GLOBAL_FRAMES.clear();
        for (AnimatedEmojis animatedEmoji : animatedEmojis) {
            ANIMATED_GLOBAL_FRAMES.put(animatedEmoji.BaseCodePoint, animatedEmoji.frames);
        }
    }
}
