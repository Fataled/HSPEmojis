package net.Fataled.hspemojis.client;

import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class EmojiRegistry {
    private EmojiRegistry() {
    }

    // You may need to know a little hexadecimal but  all you do it follow the pattern 1st string is the emojis name and the second on is the pua code used,
    // the way you spell something here is how it's going to be spelled in game
    public static final Map<String, String> Tokens = Map.ofEntries(
            Map.entry(":meow:", "\uE900"),
            Map.entry(":fu:", "\uE901"),
            Map.entry(":pol:", "\uE902"),
            Map.entry(":artempetuh:", "\uE903"),
            Map.entry(":true:", "\uE904"),
            Map.entry(":cordeliaheart:", "\uE905"),
            Map.entry(":fear:", "\uE906"),
            Map.entry(":ashbaby:", "\uE907"),
            Map.entry(":baer:", "\uE908"),
            Map.entry(":aswhat:", "\uE909"),
            Map.entry(":automaid:", "\uE90A"),
            Map.entry(":buggati:", "\uE90B"),
            Map.entry(":business:", "\uE90C"),
            Map.entry(":freedom:", "\uE90D"),
            Map.entry(":hemorrhage:", "\uE90E"),
            Map.entry(":nyabones:", "\uE90F"),
            Map.entry(":pog:", "\uE910"),
            Map.entry(":slurp:", "\uE911"),
            Map.entry(":uno:", "\uE912")
    );

    static List<Emoji> emojis = getEmojis();

    public static boolean containsAnyToken(String s) {
        for (Emoji emoji : emojis) {
            if (!s.contains(emoji.EmojiName)) return true;
        }
        return false;
    }

    private static @NotNull List<Emoji> getEmojis() {
        return List.of(
        new Emoji(":meow:",  SoundEvents.ENTITY_CAT_AMBIENT, 0xE900),
        new Emoji(":fu:",  null, 0xE901),
        new Emoji(":pointandlaugh:",  null, 0xE902),
        new Emoji(":artempetuh:",  null, 0xE903),
        new Emoji(":true:",  null, 0xE904),
        new Emoji(":cordeliaheart:",  null, 0xE905),
        new Emoji(":fear:",null, 0xE906),
        new Emoji(":ashbaby:", null, 0xE907),
        new Emoji(":baer:", null, 0xE908),
        new Emoji(":aswhat:",  null, 0xE909),
        new Emoji(":automaid:", null, 0xE90A),
        new Emoji(":buggati:",  null, 0xE90B),
        new Emoji(":business:", null, 0xE90C),
        new Emoji(":icanfinallyki:", null, 0xE90D),
        new Emoji(":hemorrhage:", null, 0xE90E),
        new Emoji(":nyabones:", null, 0xE90F),
        new Emoji(":pog:",  null, 0xE910),
        new Emoji(":slurp:", SoundEvents.ENTITY_GENERIC_DRINK.value(), 0xE911),
        new Emoji(":uno:", null, 0xE912),
        new Emoji(":doom:",  SoundEvents.AMBIENT_CAVE.value(), 0xE913),
        new Emoji(":donot:",  SoundEvents.ENTITY_PILLAGER_AMBIENT, 0xE914),
        new Emoji(":greed:",  null, 0xE915),
        new Emoji(":application:",  null, 0xE916) ,
        new Emoji(":plus1",  null, 0xE917),
        new AnimatedEmojis(":skeletonbangingshield:", null, 0xE9018, new int[]{0xE919, 0xE91A}, 4)

        );
    }

    public static final List<Emoji> EMOJIS = getEmojis();

public static final Map<String, Emoji> EMOJI_BY_TOKEN =
        EMOJIS.stream().collect(java.util.stream.Collectors.toUnmodifiableMap(
                Emoji::getEmojiName,
                e -> e
        ));

}