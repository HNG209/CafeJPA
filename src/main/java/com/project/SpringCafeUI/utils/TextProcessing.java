package com.project.SpringCafeUI.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextProcessing {
    public static String formatString(String input) {
        // Loại bỏ dấu tiếng Việt
        String withoutAccents = removeAccent(input);

        // Chuyển đổi các ký tự thành chữ thường
        String lowerCase = withoutAccents.toLowerCase();

        // Thay thế khoảng trắng bằng dấu gạch ngang
        String replacedSpaces = lowerCase.replaceAll("\\s+", "-");

        // Loại bỏ các ký tự không phải chữ cái hoặc số hoặc dấu gạch ngang
        String cleanedString = replacedSpaces.replaceAll("[^a-z0-9\\-]", "");

        return cleanedString;
    }

    private static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d");
    }
}