package com.leetcode2.org.StringDemo;

import java.util.*;

// AC 自动机

public class ACAutomaton {

    public static void main(String[] args) {
        // 定义模式字符串数组
        String[] patterns = {"he", "she", "hers", "his"};

        // 定义文本字符串
        String text = "hershehishers";

        // 构建 Trie 树
        Trie trie = new Trie();
        for (String pattern : patterns) {
            trie.insert(pattern);
        }

        // 构建失败链接
        ACBuilder.buildFailureLinks(trie.root);

        // AC 自动机匹配
        List<int[]> matches = ACAutomaton.acAutomaton(text, trie.root, patterns[0].length());

        // 打印匹配结果
        System.out.println("Matches: " + matches);
    }
    public static List<int[]> acAutomaton(String text, TrieNode root, int patternLength) {
        TrieNode current = root;
        List<int[]> results = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (current != null && !current.children.containsKey(c)) {
                current = current.failureLink;
            }

            if (current == null) {
                current = root;
                continue;
            }

            current = current.children.get(c);

            if (current.isEndOfWord) {
                results.add(new int[]{i - patternLength + 1, i});
            }
        }

        return results;
    }
}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    TrieNode failureLink;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.failureLink = null;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c :
                word.toCharArray()) {
            node.children.computeIfAbsent(c, k -> new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }
}

class ACBuilder {
    public static void buildFailureLinks(TrieNode root) {
        Queue<TrieNode> queue = new LinkedList<>();
        for (TrieNode child : root.children.values()) {
            child.failureLink = root;
            queue.add(child);
        }
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char c = entry.getKey();
                TrieNode child = entry.getValue();
                queue.add(child);
                TrieNode failureLink = current.failureLink;
                while (failureLink != null && !failureLink.children.containsKey(c)) {
                    failureLink = failureLink.failureLink;
                }
                child.failureLink = (failureLink != null) ? failureLink.children.get(c) : root;
            }
        }
    }

}
