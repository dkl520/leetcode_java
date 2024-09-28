package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> mapStr = new HashMap<>();
        String alphabetString = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        for (int i = 2; i < 10; i++) {
            String str = "";
            if (i == 7 || i == 9) {
                str = alphabetString.substring(index, index + 4);
                index += 4;
            } else {
                str = alphabetString.substring(index, index + 3);
                index += 3;
            }
            char key = (char) (i + '0');
            mapStr.put(key, str);
        }
        ArrayList<String> combination = new ArrayList<>();
        List<String> results = new ArrayList<>();
        if (digits.isEmpty()){
            return  results;
        }
        for (char num : digits.toCharArray()) {
            String str = mapStr.get(num);
            combination.add(mapStr.get(num));
        }

        tracebackArr(combination, results, 0, new StringBuilder(""));
        return  results;
    }
    static void tracebackArr(ArrayList<String> combination, List<String> results, int index, StringBuilder str) {
        if (str.length() == combination.size()) {
            results.add(String.valueOf(str));
            return;
        }
        String current = combination.get(index);
        for (char c : current.toCharArray()) {
            str.append(c);
            index++;
            tracebackArr(combination, results, index, str);
            index--;
            str.deleteCharAt(str.length() - 1);
        }
    }

    public static void main(String[] args) {
        Map<Character, String> mapStr = new HashMap<>();
        String alphabetString = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        for (int i = 2; i < 10; i++) {
            String str = "";
            if (i == 7 || i == 9) {
                str = alphabetString.substring(index, index + 4);
                index += 4;
            } else {
                str = alphabetString.substring(index, index + 3);
                index += 3;
            }
            char key = (char) (i + '0');
            mapStr.put(key, str);
        }
        String digits = "2435";
        ArrayList<String> combination = new ArrayList<>();
        for (char num : digits.toCharArray()) {
            String str = mapStr.get(num);
            combination.add(mapStr.get(num));
        }
        List<String> results = new ArrayList<>();
        tracebackArr(combination, results, 0, new StringBuilder(""));
//        return results;
        System.out.println(mapStr);
    }


}