package com.example.tasks.task_two.remade;

import java.util.HashSet;

public class Algorithms {

    private static HashSet<Character> vowelSet = new HashSet<>();

    static {
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
    }

    public static String newRecursiveMethod(String str) {
        StringBuilder out = new StringBuilder();
        char[] in = str.toCharArray();
        int length = str.length();
        String vowels = "aeiou";
        doAsterisk(out, in, 0, length, vowels);
        return out.toString();
    }

    private static void doAsterisk(StringBuilder out, char[] in, int index, int length, String vowels) {
        if (index == length) {
            if (vowels.contains(String.valueOf(in[length - 1]))) out.setLength(out.length() - 1);
            return;
        }
        try {
            if (!vowels.contains(String.valueOf(in[index]))) out.append(in[index]);
            else if (index == 0) out.append(in[index]).append("*");
            else if (out.charAt(out.length() - 1) == '*') out.append(in[index]).append("*");
            else out.append("*").append(in[index]).append("*");
        } finally {
            doAsterisk(out, in, index + 1, length, vowels);
        }
    }

    public static String messageCoding(String str) {
        StringBuilder resultBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                resultBuilder.append("00 0");
                i = countElements(resultBuilder, chars[i], i, chars);
            } else {
                resultBuilder.append("0 0");
                i = countElements(resultBuilder, chars[i], i, chars);
            }
        }
        return new String(resultBuilder);
    }

    public static String newRecursiveMethodTest(String str) {
        StringBuilder out = new StringBuilder();
        char[] in = str.toCharArray();
        int length = str.length();
        doAsteriskTest(out, in, 0, length);
        return out.toString();
    }

    private static void doAsteriskTest(StringBuilder out, char[] in, int index, int length) {
        if (index == length) {
            if (vowelSet.contains(in[length - 1])) out.setLength(out.length() - 1);
            return;
        }
        try {
            if (!vowelSet.contains(in[index])) {
                out.append(in[index]);
            } else if (index == 0) {
                out.append(in[index]).append("*");
            } else if (out.charAt(out.length() - 1) == '*') {
                out.append(in[index]).append("*");
            } else {
                out.append("*").append(in[index]).append("*");
            }
        } finally {
            doAsteriskTest(out, in, index + 1, length);
        }
    }

    private static int countElements(StringBuilder resultBuilder, char aChar, int i, char[] chars) {
        while (true) {
            if (i == chars.length - 1) return i;
            if (chars[i + 1] == aChar) {
                resultBuilder.append("0");
                i++;
            } else {
                resultBuilder.append(" ");
                return i;
            }
        }
    }
}
