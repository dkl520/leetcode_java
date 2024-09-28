package com.leetcode2.深度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution269_2 {
    /**
     * 给定一个外星语的单词表，推断出字母的顺序
     *
     * @param words 外星语单词表
     * @return 外星语字母的顺序
     */
    String alienOrder(String[] words) {
        // 用于存储所有出现的字母
        Set<Character> allAlphabets = new HashSet<>();
        // 用于存储字母之间的依赖关系的邻接表
        Map<Character, Set<Character>> edgeAlphabets = new HashMap<>();

        // 遍历所有单词，构建字母集合和依赖关系图
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                allAlphabets.add(c);
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
                        break;
                    }
                }
            }
        }

        // 进行拓扑排序以确定字母顺序
        List<Character> results = topologicalSort(edgeAlphabets, allAlphabets);
        // 将结果列表转换为字符串
        return results.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * 对字母进行拓扑排序
     *
     * @param edgeAlphabets 字母之间的依赖关系图
     * @param allAlphabets  所有出现的字母
     * @return 拓扑排序后的字母列表
     */
    List<Character> topologicalSort(Map<Character, Set<Character>> edgeAlphabets, Set<Character> allAlphabets) {
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        // 计算所有字母中哪些字母没有依赖关系（即没有出现在任何其他字母的依赖列表中）
        Set<Character> difference = new HashSet<>(allAlphabets);
        Set<Character> values = edgeAlphabets.values().stream().reduce(new HashSet<>(), (acc, list) -> {
            acc.addAll(list);
            return acc;
        });
        difference.removeAll(values);

        // 从第一个没有依赖的字母开始
        for (Character firstAlphabet : difference) {
            if (!visited.contains(firstAlphabet)) {
                explore(firstAlphabet, edgeAlphabets, stack, visited);
            }
        }

        // 反转栈以获得正确的拓扑顺序
        Collections.reverse(stack);
        return new ArrayList<>(stack);
    }

    /**
     * 深度优先搜索遍历字母及其依赖关系
     *
     * @param firstAlphabet 当前字母
     * @param edgeAlphabets 字母之间的依赖关系图
     * @param stack         存储结果的栈
     * @param visited       访问过的字母集合
     */
    private void explore(Character firstAlphabet, Map<Character, Set<Character>> edgeAlphabets, Stack<Character> stack, Set<Character> visited) {
        visited.add(firstAlphabet);
        if (edgeAlphabets.containsKey(firstAlphabet)) {
            for (Character c : edgeAlphabets.get(firstAlphabet)) {
                if (!visited.contains(c)) {
                    explore(c, edgeAlphabets, stack, visited);
                }
            }
        }
        stack.push(firstAlphabet);
    }

    public static void main(String[] args) {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(new Solution269_2().alienOrder(words));
    }
}


