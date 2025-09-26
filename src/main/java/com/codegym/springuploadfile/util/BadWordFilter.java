package com.codegym.springuploadfile.util;

import java.util.Arrays;
import java.util.List;

public class BadWordFilter {
    private static final List<String> BAD_WORDS = Arrays.asList(
            "xau", "tuc", "che", "ngo", "batlichsu" // Thêm các từ xấu khác tại đây
    );

    public static boolean containsBadWord(String text) {
        if (text == null) return false;
        String lowerText = text.toLowerCase();
        for (String badWord : BAD_WORDS) {
            if (lowerText.contains(badWord)) {
                return true;
            }
        }
        return false;
    }
}

