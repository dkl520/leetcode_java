package com.leetcode2.org.数据结构;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children; // 子节点
    boolean isEndOfWord; // 标记是否为单词的结束

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class Trie {
    private final TrieNode root; // 根节点

    public Trie() {
        root = new TrieNode(); // 初始化根节点
    }

    // 插入字符串
    public void insert(String word) {
        TrieNode node = root; // 从根节点开始
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode()); // 如果没有该字符的子节点，则创建
            node = node.children.get(c); // 移动到子节点
        }
        node.isEndOfWord = true; // 标记单词结束
    }

    // 查找字符串
    public boolean search(String word) {
        TrieNode node = root; // 从根节点开始
        for (char c : word.toCharArray()) {
            node = node.children.get(c); // 获取当前字符的子节点
            if (node == null) { // 如果子节点不存在，则返回 false
                return false;
            }
        }
        return node.isEndOfWord; // 返回是否为单词结束
    }

    // 查找前缀
    public boolean startsWith(String prefix) {
        TrieNode node = root; // 从根节点开始
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c); // 获取当前字符的子节点
            if (node == null) { // 如果子节点不存在，则返回 false
                return false;
            }
        }
        return true; // 前缀存在
    }

    // 测试用例
    public static void main(String[] args) {
        Trie trie = new Trie();

        // 插入字符串
        trie.insert("apple");
        trie.insert("app");

        // 查找字符串
        System.out.println(trie.search("apple")); // 输出: true
        System.out.println(trie.search("app"));   // 输出: true
        System.out.println(trie.search("appl"));  // 输出: false

        // 查找前缀
        System.out.println(trie.startsWith("ap")); // 输出: true
        System.out.println(trie.startsWith("b"));   // 输出: false
    }
}
