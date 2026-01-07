package net.Fataled.hspemojis.client;

import net.minecraft.text.*;

public final class TextEmojiRewriter {
    private TextEmojiRewriter() {
    }

    public static Text rewrite(Text in) {
        MutableText out;

        TextContent content = in.getContent();

        // 1.21.x Yarn: literal content
        if (content instanceof PlainTextContent.Literal literal) {
            out = rewriteLiteral(literal.string(), in.getStyle());
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
        // fast exit
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

            // insert glyph with same styling but emoji font
            String glyph = EmojiRegistry.Tokens.get(m.token);
            out.append(Text.literal(glyph).setStyle(baseStyle.withFont(EmojiText.EMOJI_FONT)));

            i = m.start + m.token.length();
        }

        return out;
    }

    private static boolean containsAnyToken(String s) {
        for (String token : EmojiRegistry.Tokens.keySet()) {
            if (s.contains(token)) return true;
        }
        return false;
    }

    private static Match findNextToken(String s, int from) {
        int bestIdx = -1;
        String bestToken = null;

        for (String token : EmojiRegistry.Tokens.keySet()) {
            int idx = s.indexOf(token, from);
            if (idx != -1 && (bestIdx == -1 || idx < bestIdx)) {
                bestIdx = idx;
                bestToken = token;
            }
        }

        return bestToken == null ? null : new Match(bestIdx, bestToken);
    }

    private record Match(int start, String token) {}
}

