package org.test.sinteka;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/main/resources/input.txt");
        File outputFile = new File("src/main/resources/output.txt");
        List<String> listOne = separateFirstList(fileToList(inputFile));
        List<String> listTwo = separateSecondList(fileToList(inputFile));
        compareAndOutput(listOne, listTwo, outputFile);
    }

    public static List<String> fileToList(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
       for (int i = 0; i < list.size(); i++) {
           if (list.get(i).isEmpty()) {
               list.remove(i);
           }
       }
        return list;
    }

    public static List<String> separateFirstList(List<String> list) {
        int n = Integer.parseInt(list.get(0));
        List<String> newList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static List<String> separateSecondList(List<String> list) {
        List<String> comparedList = separateFirstList(list);
        for (int i = 0; i < list.size(); i++) {
            for (String s : comparedList)
                if (list.get(i).equals(s)) {
                    list.remove(i);
                }
        }
        list.remove(0);
        List<String> newList = new ArrayList<>();
        int m = Integer.parseInt(list.get(0));
        for (int i = 1; i <= m; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static List<String> wordsOfList(List<String> list) {
        List<String> newList;
        List<String> wordsList = new ArrayList<>();
        for (String str : list) {
            newList = Arrays.stream(str.split("\\s")).collect(Collectors.toList());
            wordsList.addAll(newList);
        }
        return wordsList;
    }

    public static void compareAndOutput(List<String> listOne, List<String> listTwo, File file) throws IOException {
        List<String> wordsOfList = wordsOfList(listOne);
        HashSet<String> finalSet = comparingLists(listOne, listTwo, wordsOfList);
        FileWriter fileWriter = new FileWriter(file);
        for (String str : finalSet) {
            fileWriter.write(str + "\n");
        }
        fileWriter.close();
    }

    public static HashSet<String> comparingLists(List<String> listOne, List<String> listTwo, List<String> compList) {
        HashSet<String> set = new HashSet<>();
        for (String str : compList) {
            for (String s2 : listTwo) {
                for (String s1 : listOne) {
                    if (s1.contains(str) && s2.contains(str)) {
                        set.add(s1 + ":" + s2);
                    }
                }
            }
        }
        return set;
    }

}