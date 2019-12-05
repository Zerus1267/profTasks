package com.example.tasks.task_two.remade;

import com.example.tasks.task_one.remade.Worker;

import java.lang.reflect.Field;
import java.util.*;

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

    public static void newMergeMethod(List<Worker> workersA, List<Worker> workersB) throws IllegalAccessException {
        HashSet<Integer> idB = new HashSet<>();
        HashMap<Integer, Integer> mapA = new HashMap<>(); // Map for binding the ID's of workers in ListA and its' index in the ListA
        for (Worker worker : workersB) {
            idB.add(worker.getId());
        }
        // Delete elements from listA if they are not in ListB
        workersA.removeIf(worker -> !idB.contains(worker.getId()));

        for (Worker worker : workersA) {
            mapA.put(worker.getId(), workersA.indexOf(worker));
        }
        for (Worker worker : workersB) {
            int temp = worker.getId();
            if (mapA.containsKey(temp)) {
                mergeUniversal(workersA.get(mapA.get(temp)), worker);
            } else {
                workersA.add(worker);
            }
        }
    }

    private static void getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

    }

    private static boolean isInternal(String name) {
        return name.startsWith("java.") || name.startsWith("javax.")
                || name.startsWith("com.sun.")
                || name.startsWith("oracle.");
    }

    public static <T> T mergeUniversal(T objA, T objB) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        getAllFields(fields, objA.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(objB) == null) {
                field.setAccessible(false);
                continue;
            }
            if (!isInternal(field.get(objB).getClass().getName())) {
                field.set(objA, mergeUniversal(field.get(objA), field.get(objB)));
            } else {
                field.set(objA, field.get(objB));
            }
            field.setAccessible(false);
        }
        return objA;
    }


}
