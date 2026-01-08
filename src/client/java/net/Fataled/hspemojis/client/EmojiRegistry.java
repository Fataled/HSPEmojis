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
            if (s.contains(emoji.EmojiName)) return true;
        }
        return false;
    }

    private static @NotNull List<Emoji> getEmojis() {
        return List.of(
        new Emoji(":meow:", "\uE900", SoundEvents.ENTITY_CAT_AMBIENT),
        new Emoji(":fu:", "\uE901", null),
        new Emoji(":pol:", "\uE902", null),
        new Emoji(":artempetuh:", "\uE903", null),
        new Emoji(":true:", "\uE904", null),
        new Emoji(":cordeliaheart:", "\uE905", null),
        new Emoji(":fear:", "\uE906", null),
        new Emoji(":ashbaby:", "\uE907", null),
        new Emoji(":baer:", "\uE908", null),
        new Emoji(":aswhat:", "\uE909", null),
        new Emoji(":automaid:", "\uE90A", null),
        new Emoji(":buggati:", "\uE90B", null),
        new Emoji(":business:", "\uE90C", null),
        new Emoji(":freedom:", "\uE90D", null),
        new Emoji(":hemorrhage:", "\uE90E", null),
        new Emoji(":nyabones:", "\uE90F", null),
        new Emoji(":pog:", "\uE910", null),
        new Emoji(":slurp:", "\uE911", SoundEvents.ENTITY_GENERIC_DRINK.value()),
        new Emoji(":uno:", "\uE912", null),
        new Emoji(":doom:", "\uE913", SoundEvents.AMBIENT_CAVE.value()),
        new Emoji(":donot:", "\uE914", SoundEvents.ENTITY_PILLAGER_AMBIENT)
        );
    }

    public static final List<Emoji> EMOJIS = getEmojis();

public static final Map<String, Emoji> EMOJI_BY_TOKEN =
        EMOJIS.stream().collect(java.util.stream.Collectors.toUnmodifiableMap(
                Emoji::getEmojiName,
                e -> e
        ));

}