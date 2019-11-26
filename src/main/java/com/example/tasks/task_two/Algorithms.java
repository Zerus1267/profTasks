package com.example.tasks.task_two;

import com.example.tasks.task_one.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Algorithms {

    public Algorithms() {
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
            System.out.println(out.toString());
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

    private static int getFirstVowelIndex(String str) {
        String vowels = "aeiou";
        for (int i = 0; i < str.length(); i++) {
            if (vowels.contains(String.valueOf(str.charAt(i)))) return i;
        }
        return -1;
    }

    public static String interviewRecursionTest(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!str.contains("*")) {
            int firstVowelIndex = getFirstVowelIndex(str);
            if (firstVowelIndex == -1) return "There are no vowels in the String";
            else if (firstVowelIndex != 0) {
                stringBuilder.insert(firstVowelIndex, "*");
                stringBuilder.insert(firstVowelIndex + 2, "*");
                stringBuilder.insert(firstVowelIndex + 3, "|");
                return interviewRecursionTest(stringBuilder.toString());
            } else {
                stringBuilder.insert(firstVowelIndex + 1, "*");
                stringBuilder.insert(firstVowelIndex + 2, "|");
                return interviewRecursionTest(stringBuilder.toString());
            }
        } else {
            int lastLineIndex = stringBuilder.lastIndexOf("|");
            char nextChar;
            try {
                nextChar = stringBuilder.charAt(lastLineIndex + 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringBuilder.deleteCharAt(lastLineIndex);
                return stringBuilder.toString();
            }
            if (nextChar == 'a' || nextChar == 'e' || nextChar == 'i' || nextChar == 'o' || nextChar == 'u') {
                if (lastLineIndex + 1 != stringBuilder.length() - 1) {
                    stringBuilder.insert(lastLineIndex + 2, "*");
                    stringBuilder.insert(lastLineIndex + 3, "|");
                    stringBuilder.deleteCharAt(lastLineIndex);
                    return interviewRecursionTest(stringBuilder.toString());
                } else {
                    stringBuilder.insert(lastLineIndex + 1, "*");
                    stringBuilder.deleteCharAt(lastLineIndex);
                    return stringBuilder.toString();
                }
            } else {
                if (lastLineIndex != stringBuilder.length() - 1) {
                    stringBuilder.insert(lastLineIndex + 2, "|");
                    stringBuilder.deleteCharAt(lastLineIndex);
                    return interviewRecursionTest(stringBuilder.toString());
                } else {
                    return stringBuilder.toString();
                }
            }
        }
    }

    public static String nonRecursiveInterviewTest(String str) {
        String lowerStr = str.toLowerCase();
        char[] chars = lowerStr.toCharArray();
        char[] initialChars = str.toCharArray();
        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        for (char c : chars) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                indexes.add(i);
            }
            i++;
        }
        Stream<Character> characterStream = IntStream.range(0, initialChars.length).mapToObj(t -> initialChars[t]);
        List<Character> characterList = characterStream.collect(Collectors.toList());
        for (int j = indexes.size() - 1; j >= 0; j--) {
            if (indexes.get(j) == characterList.size() - 1) {
                characterList.add(indexes.get(j), '*');
            } else if (characterList.get(indexes.get(j) + 1) == '*' && indexes.get(j) != 0) {
                characterList.add(indexes.get(j), '*');
            } else if (characterList.get(indexes.get(j) + 1) == '*' && indexes.get(j) == 0) break;
            else if (indexes.get(j) == 0 && characterList.get(indexes.get(j) + 1) != '*') {
                characterList.add(indexes.get(j) + 1, '*');
            } else {
                characterList.add(indexes.get(j), '*');
                characterList.add(indexes.get(j) + 2, '*');
            }
        }
        return characterList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static String messageCoding(String str) {
        // str = "01000111"
        StringBuilder resultBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            try {
                if (chars[i] == '0') {
                    resultBuilder.append("00 0");
                    while (true) {
                        if (chars[i + 1] == '0') {
                            resultBuilder.append("0");
                            i++;
                        } else {
                            resultBuilder.append(" ");
                            break;
                        }
                    }
                } else {
                    resultBuilder.append("0 0");
                    while (true) {
                        if (chars[i + 1] == '1') {
                            resultBuilder.append("0");
                            i++;
                        } else {
                            resultBuilder.append(" ");
                            break;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return new String(resultBuilder);
    }

    public static List<Worker> mergeWorkersLists(List<Worker> workersOld, List<Worker> workersNew) {
        //List<Worker> newWorkerList = new ArrayList<>();
        List<Worker> safeWorkersNew = new ArrayList<>(workersNew);
        boolean flag = true;
        for (int i = 0; i < workersOld.size(); i++) {
            for (int j = 0; j < safeWorkersNew.size(); j++) {
                if (!workersOld.get(i).equals(safeWorkersNew.get(j)) && safeWorkersNew.get(j).getId() == workersOld.get(i).getId()) {
                    workersOld.set(i, safeWorkersNew.get(j));
                    safeWorkersNew.remove(j);
                    flag = true;
                    break;
                } else if (workersOld.get(i).equals(safeWorkersNew.get(j))) {
                    safeWorkersNew.remove(j);
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                workersOld.remove(i);
                i--;
            }
        }
        workersOld.addAll(safeWorkersNew);
        return workersOld;
    }
}
