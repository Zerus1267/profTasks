package com.example.tasks.task_two.remade2;

import com.example.tasks.task_one.remade.Worker;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class RemadeAlgorithms {

    private static final int STRING_START = 0;
    private static final int INTEGER_ONE = 1;
    private static final int INTEGER_TWO = 2;
    private static final String ASTERISK = "*";
    private static final String SPACE_SYMBOL = " ";
    private static final String ZERO_SYMBOL = "0";
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

    private static String testRec(String str, int length) { // OLD VERSION
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

    public static List<Worker> mergeTwoWorkerLists(List<Worker> listA, List<Worker> listB) throws IllegalAccessException {
        Set<Worker> workersA = new HashSet<>(listA);
        Set<Worker> workersB = new HashSet<>(listB);
        Map<Integer, Worker> workerMap = new HashMap<>();
        for (Worker worker : workersA) {
            workerMap.put(worker.getId(), worker);
        }
        return newMergeMethod(workerMap, workersB);
    }

    private static List<Worker> newMergeMethod(Map<Integer, Worker> workersA, Set<Worker> workersB) throws IllegalAccessException {

        // Delete elements from listA if they are not in ListB
        workersA.entrySet().removeIf(integerWorkerEntry -> !workersB.contains(integerWorkerEntry.getValue()));

        for (Worker worker : workersB) {
            if (workersA.containsKey(worker.getId())) {
                mergeUniversal(workersA.get(worker.getId()), worker);
            } else {
                workersA.put(worker.getId(), worker);
            }
        }
        return new ArrayList<>(workersA.values());
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

    public static String lastRecursion(String str) { // NEW RECURSION
        if (str.length() == INTEGER_ONE) {
            return str;
        }
        char currentChar = str.charAt(STRING_START);
        String lastPart = String.valueOf(currentChar);
        if (vowelSet.contains(currentChar) || vowelSet.contains(str.charAt(INTEGER_ONE)) && str.length() == INTEGER_TWO || !vowelSet.contains(currentChar) && vowelSet.contains(str.charAt(INTEGER_ONE))) {
            lastPart = currentChar + ASTERISK;
        }
        return lastPart + lastRecursion(str.substring(INTEGER_ONE, str.length()));
    }

    public static String messageCodingNew(String str) {
        List<Character> characterList = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        char currentChar;
        StringBuilder stringBuilder = new StringBuilder();
        ListIterator<Character> iterator = characterList.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next() == '0') {
                stringBuilder.append("00 0");
                currentChar = '0';
            } else {
                stringBuilder.append("0 0");
                currentChar = '1';
            }
            while (iterator.hasNext()) {
                if (iterator.next() == currentChar) {
                    stringBuilder.append("0");
                } else {
                    iterator.previous();
                    stringBuilder.append(" ");
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String generateHead(char currentChar) {
        String stringResult;
        if (currentChar == '0') {
            stringResult = "00 0";
        } else {
            stringResult = "0 0";
        }
        return stringResult;
    }

    public static String extremeMessageCoding(String str){
        List<Character> characterList = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        ListIterator<Character> iterator = characterList.listIterator();
        char globalChar = iterator.next();
        char localChar;
        stringBuilder.append(generateHead(globalChar));
        while (iterator.hasNext()) {
            localChar = iterator.next();
            if (localChar == globalChar) {
                stringBuilder.append(ZERO_SYMBOL);
            } else {
                stringBuilder.append(SPACE_SYMBOL);
                globalChar = localChar;
                stringBuilder.append(generateHead(globalChar));
            }
        }
        return stringBuilder.toString();
    }
}
