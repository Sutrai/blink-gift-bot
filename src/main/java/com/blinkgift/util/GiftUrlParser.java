package com.blinkgift.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GiftUrlParser {
    private static final Pattern NFT_URL_PATTERN = Pattern.compile("t\\.me/nft/([a-zA-Z]+)-(\\d+)");

    public static String formatUrlToName(String url) {
        Matcher matcher = NFT_URL_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group(1) + " #" + matcher.group(2); // TrappedHeart #8442
        }
        return null;
    }
}