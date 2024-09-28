package com.leetcode2.深度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution269 {

    /**
     * 给定一个外星语的单词表，推断出字母的顺序
     * @param words 外星语单词表
     * @return 外星语字母的顺序
     */
    String alienOrder(String[] words) {
        // 用于存储结果的栈
        Stack<Character> stack = new Stack<>();

        // 提取所有单词的第一个字母并转换为数组
        Character[] firstAlphabetsList = Arrays.stream(words).map(word -> word.charAt(0)).toArray(Character[]::new);

        // 用于存储所有出现的字母
        Set<Character> allAlphabets = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                allAlphabets.add(word.charAt(i));
            }
        }

        // 用于存储字母之间的依赖关系的邻接表
        Map<Character, Set<Character>> edgeAlphabets = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];
            int firstIndex = 0;
            int secondIndex = 0;

            // 找到第一个不同的字母并建立依赖关系
            while (firstIndex < firstWord.length() && secondIndex < secondWord.length()) {
                if (firstWord.charAt(firstIndex) != secondWord.charAt(secondIndex)) {
                    Set<Character> list = edgeAlphabets.getOrDefault(firstWord.charAt(firstIndex), new HashSet<Character>());
                    list.add(secondWord.charAt(secondIndex));
                    edgeAlphabets.put(firstWord.charAt(firstIndex), list);
                    break;
                }
                firstIndex++;
                secondIndex++;
            }
        }

        // 进行拓扑排序以确定字母顺序
        List<Character> results = topologicalSort(edgeAlphabets, allAlphabets);
        // 将结果列表转换为字符串
        return results.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * 对字母进行拓扑排序
     * @param edgeAlphabets 字母之间的依赖关系图
     * @param allAlphabets 所有出现的字母
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
        Character firstAlphabet = (Character) difference.toArray()[0];
        explore(firstAlphabet, edgeAlphabets, stack, visited);

        // 反转栈以获得正确的拓扑顺序
        Collections.reverse(stack);
        return new ArrayList<>(stack);
    }

    /**
     * 深度优先搜索遍历字母及其依赖关系
     * @param firstAlphabet 当前字母
     * @param edgeAlphabets 字母之间的依赖关系图
     * @param stack 存储结果的栈
     * @param visited 访问过的字母集合
     */
    private void explore(Character firstAlphabet, Map<Character, Set<Character>> edgeAlphabets, Stack<Character> stack, Set<Character> visited) {
        if (edgeAlphabets.containsKey(firstAlphabet)) {
            Set<Character> set = edgeAlphabets.get(firstAlphabet);
            for (Character c : set) {
                if (!visited.contains(c)) {
                    visited.add(c);
                    explore(c, edgeAlphabets, stack, visited);
                }
            }
        }
        stack.push(firstAlphabet);
    }

    public static void main(String[] args) {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(new Solution269().alienOrder(words));
    }
}

//O(n)+O(L)+O(V+E)+O(V)=O(L)+O(V+E)
