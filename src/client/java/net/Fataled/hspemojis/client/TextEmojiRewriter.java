package net.Fataled.hspemojis.client;

import net.minecraft.text.*;

import static net.Fataled.hspemojis.client.EmojiRegistry.*;

public final class TextEmojiRewriter {
    private TextEmojiRewriter() {
    }

    public static Text rewrite(Text in) {
        MutableText out;

        TextContent content = in.getContent();

        // 1.21.x Yarn: literal content
        if (content instanceof PlainTextContent.Literal(String string)) {
            out = rewriteLiteral(string, in.getStyle());
        } else {
            // keep node exactly, but we’ll rewrite and re-append its siblings
            out = in.copy();
            out.getSiblings().clear();
        }

        for (Text sib : in.getSiblings()) {
            out.append(rewrite(sib));
        }

        return out;
    }

    private static MutableText rewriteLiteral(String s, Style baseStyle) {
    if (!containsAnyToken(s)) {
        return Text.literal(s).setStyle(baseStyle);
    }

    MutableText out = Text.empty();
    int i = 0;

    while (i < s.length()) {
        Match m = findNextToken(s, i);
        if (m == null) {
            out.append(Text.literal(s.substring(i)).setStyle(baseStyle));
            break;
        }

        if (m.start > i) {
            out.append(Text.literal(s.substring(i, m.start)).setStyle(baseStyle));
        }

        Emoji emoji = m.emoji();

        // token in text like ":meow:"
        String token = emoji.getEmojiName();

        // glyph we insert like "\uE900"
        String glyph = emoji.getEmojiId();

        out.append(
            Text.literal(glyph).styled(style ->
                Style.EMPTY
                    .withColor(TextColor.fromRgb(0xFFFFFF))
                    .withBold(false)
                    .withItalic(false)
                    .withUnderline(false)
                    .withStrikethrough(false)
                    .withObfuscated(false)
                    .withFont(EmojiText.EMOJI_FONT)
            )
        );

        // move past the token we matched in the original string
        i = m.start + token.length();
    }

    return out;
}

    public static Match findNextToken(String s, int from) {
    int bestIdx = -1;
    Emoji bestEmoji = null;

    for (Emoji emoji : EMOJIS) {
        String token = emoji.getEmojiName(); // ":meow:" token in the message
        int idx = s.indexOf(token, from);

        if (idx != -1 && (bestIdx == -1 || idx < bestIdx)) {
            bestIdx = idx;
            bestEmoji = emoji; // keep the matched emoji
        }
    }

    return bestEmoji == null ? null : new Match(bestIdx, bestEmoji);
}
    public record Match(int start, Emoji emoji) {}

    public static java.util.Set<Emoji> findEmojisIn(String s) {
        java.util.Set<Emoji> found = new java.util.HashSet<>();
        int from = 0;

        while (from < s.length()) {
            TextEmojiRewriter.Match m = findNextToken(s, from);
            if (m == null) break;

            Emoji e = m.emoji();
            found.add(e);

            // advance past the matched token in the original string
            from = m.start + e.getEmojiName().length();
        }

        return found;
    }


}

