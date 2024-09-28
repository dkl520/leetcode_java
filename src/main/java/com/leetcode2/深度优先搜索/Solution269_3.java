package com.leetcode2.深度优先搜索;


import java.util.*;
import java.util.stream.Collectors;

public class Solution269_3 {

    /**
     * 给定一个外星语的单词表，推断出字母的顺序
     * @param words 外星语单词表
     * @return 外星语字母的顺序
     */
    String alienOrder(String[] words) {
        // 用于存储所有出现的字母
        Set<Character> allAlphabets = new HashSet<>();
        // 用于存储字母之间的依赖关系的邻接表
        Map<Character, Set<Character>> edgeAlphabets = new HashMap<>();
        // 用于存储字母的入度
        Map<Character, Integer> inDegree = new HashMap<>();

        // 遍历所有单词，构建字母集合和依赖关系图
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                allAlphabets.add(c);
                inDegree.putIfAbsent(c, 0);
            }
            if (i < words.length - 1) {
                String firstWord = words[i];
                String secondWord = words[i + 1];
                int len = Math.min(firstWord.length(), secondWord.length());
                for (int j = 0; j < len; j++) {
                    char firstChar = firstWord.charAt(j);
                    char secondChar = secondWord.charAt(j);
                    if (firstChar != secondChar) {
                        edgeAlphabets.computeIfAbsent(firstChar, k -> new HashSet<>()).add(secondChar);
                        inDegree.put(secondChar, inDegree.get(secondChar) + 1);
                        break;
                    }
                }
            }
        }

        // 进行拓扑排序以确定字母顺序
        List<Character> results = topologicalSort(edgeAlphabets, inDegree);
        // 将结果列表转换为字符串
        return results.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * 对字母进行拓扑排序
     * @param edgeAlphabets 字母之间的依赖关系图
     * @param inDegree 字母的入度
     * @return 拓扑排序后的字母列表
     */
    List<Character> topologicalSort(Map<Character, Set<Character>> edgeAlphabets, Map<Character, Integer> inDegree) {
        Queue<Character> queue = new LinkedList<>();
        List<Character> result = new ArrayList<>();

        // 将所有入度为0的字母加入队列
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        // 进行拓扑排序
        while (!queue.isEmpty()) {
            Character current = queue.poll();
            result.add(current);
            if (edgeAlphabets.containsKey(current)) {
                for (Character neighbor : edgeAlphabets.get(current)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // 检查是否所有字母都被排序
        if (result.size() != inDegree.size()) {
            return new ArrayList<>(); // 有循环依赖，无法完成拓扑排序
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(new Solution269_3().alienOrder(words));
    }
}
