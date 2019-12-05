package com.example.tasks.task_two.remade2;

import com.example.tasks.task_one.remade.Worker;

import java.lang.reflect.Field;
import java.util.*;

public class RemadeAlgorithms {

    private static HashSet<Character> vowelSet = new HashSet<>();

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
        if (length == 0) {
            return str;
        }
        if (vowelSet.contains(str.charAt(length - 1))) {
            if (length == str.length()) {
                return testRec(str.substring(0, length - 1) + "*" + str.charAt(length - 1), length - 1);
            } else if (length == 1) {
                return str.charAt(0) + "*" + str.substring(1);
            } else if (vowelSet.contains(str.charAt(length - 2))) {
                return testRec(str.substring(0, length - 1) + str.charAt(length - 1) + "*" + str.substring(length, str.length()), length - 1);
            } else {
                return testRec(str.substring(0, length - 1) + "*" + str.charAt(length - 1) + "*" + str.substring(length, str.length()), length - 1);
            }
        } else {
            return testRec(str, length - 1);
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
