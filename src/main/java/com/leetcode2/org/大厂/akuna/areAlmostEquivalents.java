package com.leetcode2.org.大厂.akuna;

import java.util.*;

public class areAlmostEquivalents {

    boolean[] areAlmostEquivalent(String[] s, String[] t) {
        boolean[] result = new boolean[s.length];
        outloop:
        for (int i = 0; i < s.length; i++) {
            String stringS = s[i];
            String stringT = t[i];
            if (stringS.length() != stringT.length()) {
                result[i] = false;
                continue;
            }
            Map<Character, Integer> mapS = new TreeMap<>();
            Map<Character, Integer> mapT = new TreeMap<>();
            for (int j = 0; j < stringS.length(); j++) {
                mapS.put(stringS.charAt(j), mapS.getOrDefault(stringS.charAt(j), 0) + 1);
                mapT.put(stringT.charAt(j), mapT.getOrDefault(stringT.charAt(j), 0) + 1);
            }
            Set<Character> keyAllSet = mapS.keySet();
            keyAllSet.addAll(mapT.keySet());



            for (Character key : mapS.keySet()) {
                if (Math.abs(mapS.get(key) - mapT.getOrDefault(key, 0)) > 3) {
                    result[i] = false;
                    continue outloop;
                }
            }
            for (Character key : mapT.keySet()) {
                if (Math.abs(mapT.get(key) - mapS.getOrDefault(key, 0)) > 3) {
                    result[i] = false;
                    continue outloop;
                }
            }
            result[i] = true;
        }
        return result;
    }


    boolean[] areAlmostEquivalent2(String[] s, String[] t) {
        boolean[] result = new boolean[s.length];
        for (int i = 0; i < s.length; i++) {
            String stringS = s[i];
            String stringT = t[i];
            // 长度检查
            if (stringS.length() != stringT.length()) {
                result[i] = false;
                continue;
            }
            // 使用数组代替Map，因为只处理字符
            int[] freq = new int[26];  // 假设只有小写字母
            // 一次遍历同时统计两个字符串
            for (int j = 0; j < stringS.length(); j++) {
                freq[stringS.charAt(j) - 'a']++;
                freq[stringT.charAt(j) - 'a']--;
            }
            // 检查差异
            boolean isAlmostEquivalent = true;
            for (int count : freq) {
                if (Math.abs(count) > 3) {
                    isAlmostEquivalent = false;
                    break;
                }
            }
            result[i] = isAlmostEquivalent;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] s1 = {"aabaab"};
        String[] s2 = {"bbabbc"};

        areAlmostEquivalents areAlmostEquivalents = new areAlmostEquivalents();

        System.out.println(Arrays.toString(areAlmostEquivalents.areAlmostEquivalent(s1, s2)));


    }


}
