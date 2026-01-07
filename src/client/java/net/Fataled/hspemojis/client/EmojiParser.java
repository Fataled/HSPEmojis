package net.Fataled.hspemojis.client;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public final class EmojiParser {
    private EmojiParser() {}

    public static MutableText parse(String input) {
        MutableText out = Text.empty();

        int i = 0;
        while (i < input.length()) {
            int next = -1;
            String matchedToken = null;

            // find earliest token occurrence
            for (String token : EmojiRegistry.Tokens.keySet()) {
                int idx = input.indexOf(token, i);
                if (idx != -1 && (next == -1 || idx < next)) {
                    next = idx;
                    matchedToken = token;
                }
            }

            if (next == -1) {
                out.append(Text.literal(input.substring(i)));
                break;
            }

            if (next > i) {
                out.append(Text.literal(input.substring(i, next)));
            }

            String glyph = EmojiRegistry.Tokens.get(matchedToken);
            out.append(
                Text.literal(glyph).setStyle(Style.EMPTY.withFont(EmojiText.EMOJI_FONT))
            );

            i = next + matchedToken.length();
        }

        return out;
    }
}
