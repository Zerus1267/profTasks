package com.example.tasks.task_two.remade2;

import com.example.tasks.task_one.remade.Worker;

import java.lang.reflect.Field;
import java.util.*;

public class RemadeAlgorithms {

    private static final int STRING_START = 0;
    private static final int INTEGER_ONE = 1;
    private static final int INTEGER_TWO = 2;
    private static final String ASTERISK = "*";
    private static final Set<Character> vowelSet = new HashSet<>();

    static {
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
    }

    public static String remadeRecursion(String str) {
        return testRec(str, str.length());
    }

    private static String testRec(String str, int length) {
        if (length == STRING_START) {
            return str;
        }
        if (vowelSet.contains(str.charAt(length - INTEGER_ONE))) {
            String nextStepString;
            String startString = str.substring(STRING_START, length - INTEGER_ONE);
            String endingString = str.substring(length);
            char currentChar = str.charAt(length - INTEGER_ONE);
            if (length == str.length()) {
                nextStepString = startString + ASTERISK + currentChar;
            } else if (length == INTEGER_ONE) {
                return str.charAt(STRING_START) + ASTERISK + str.substring(INTEGER_ONE);
            } else if (vowelSet.contains(str.charAt(length - INTEGER_TWO))) {
                nextStepString = startString + currentChar + ASTERISK + endingString;
            } else {
                nextStepString = startString + ASTERISK + currentChar + ASTERISK + endingString;
            }
            return testRec(nextStepString, length - INTEGER_ONE);
        }
        return testRec(str, length - INTEGER_ONE);
    }

    public static List<Worker> mergeTwoWorkerLists(List<Worker> listA, List<Worker> listB) {
        Set<Worker> workersA = new HashSet<>(listA);
        Set<Worker> workersB = new HashSet<>(listB);
        return newMergeMethod(workersA, workersB);
    }

    private static List<Worker> newMergeMethod(Set<Worker> workersA, Set<Worker> workersB) {

        // Delete elements from listA if they are not in ListB
        workersA.removeIf(worker -> !workersB.contains(worker));

        for (Worker workerB : workersB) {
            if (workersA.contains(workerB)) {
                workersA.stream()
                        .filter(worker -> Objects.equals(worker, workerB))
                        .findFirst()
                        .ifPresent(worker -> {
                            try {
                                mergeUniversal(worker, workerB);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
            } else {
                workersA.add(workerB);
            }
        }
        return new ArrayList<>(workersA);
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

    private static <T> T mergeUniversal(T objA, T objB) throws IllegalAccessException {
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
