package io.github.imtotem.shortly.util;

import org.springframework.stereotype.Component;

@Component
public class Base62 {
    private static final int BASE = 62;
    private static final char[] CODEC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public String encode(long id) {
        StringBuilder encoded = new StringBuilder();

        while (id > 0) {
            encoded.append(CODEC[(int) (id % BASE)]);
            id /= BASE;
        }

        return encoded.toString();
    }

    public long decode(String shorten) {
        int decoded = 0;
        int power = 1;

        for (int i = 0, n = shorten.length(); i < n; i++) {
            decoded += new String(CODEC).indexOf(shorten.charAt(i)) * power;
            power *= BASE;
        }

        return decoded;
    }
}
