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

    public static String interviewRecursionTest(String str){
        String lowerStr = str.toLowerCase();
        char[] chars = lowerStr.toCharArray();
        char[] initialChars = str.toCharArray();
        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        for(char c : chars){
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ){
                indexes.add(i);
            }
            i++;
        }
        Stream<Character> characterStream = IntStream.range(0,initialChars.length).mapToObj(t -> initialChars[t]);
        List<Character> characterList = characterStream.collect(Collectors.toList());
        for(int j = indexes.size()-1; j >= 0; j--){
            if(indexes.get(j) == characterList.size()-1){
                characterList.add(indexes.get(j),'*');
            }
            else if(characterList.get(indexes.get(j)+1) == '*' && indexes.get(j) != 0){
                characterList.add(indexes.get(j),'*');
            }
            else if(characterList.get(indexes.get(j)+1) == '*' && indexes.get(j) == 0) break;
            else if(indexes.get(j) == 0 && characterList.get(indexes.get(j)+1) != '*'){
                characterList.add(indexes.get(j)+1,'*');
            }
            else{
                characterList.add(indexes.get(j),'*');
                characterList.add(indexes.get(j)+2,'*');
            }
        }
        return characterList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static String messageCoding(String str){
        // str = "01000111"
        StringBuilder resultBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            try {
                if (chars[i] == '0') {
                    resultBuilder.append("00 0");
                    while (true) {
                        if (chars[i + 1] == '0') {
                            resultBuilder.append("0");
                            i++;
                        }
                        else {
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
            }
            catch (ArrayIndexOutOfBoundsException ignored){
            }
        }
        return new String(resultBuilder);
    }

    public static List<Worker> mergeWorkersLists(List<Worker> workersOld, List<Worker> workersNew){
        List<Worker> newWorkerList = new ArrayList<>();
        List<Worker> safeWorkersNew = new ArrayList<>(workersNew);
        boolean flagFirst = false;
        boolean flagSecond = false;
        for(int i = 0; i < workersOld.size(); i++){
            flagFirst = false;
            for(int j = 0; j < safeWorkersNew.size(); j++){
                if(workersOld.get(i).equals(safeWorkersNew.get(j))){
                    newWorkerList.add(safeWorkersNew.get(j));
                    safeWorkersNew.remove(j);
                    flagFirst = true;
                    break;
                }
            }
            if(!flagFirst){
                newWorkerList.add(workersOld.get(i));
            }
        }
        newWorkerList.addAll(safeWorkersNew);
        System.out.println("");
        return newWorkerList;
    }
}
