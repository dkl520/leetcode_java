package com.leetcode2.org.系统设计;


import java.util.*;

public class Solution1268 {
    static class TrieTree {
        char alpha;
        Map<Character, TrieTree> children;
        TreeSet<String> words;

        public TrieTree(Character alpha) {
            this.alpha = alpha;
            this.children = new HashMap<Character, TrieTree>();
            this.words = new TreeSet<>();
        }

        public TrieTree() {
            this.children = new HashMap<Character, TrieTree>();
            this.words = new TreeSet<>();
        }

        public void addWords(String word) {
            this.words.add(word);
            if (this.words.size() > 3) {
                this.words.remove(this.words.last());
            }
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieTree root = new TrieTree();
        TrieTree currentNode = root;
        for (String product : products) {
            currentNode = root;
            for (int i = 0; i < product.length(); i++) {
                char c = product.charAt(i);
                currentNode = currentNode.children.computeIfAbsent(c, TrieTree::new);
                currentNode.addWords(product);
            }
            System.out.println(currentNode);
        }

        List<List<String>> results = new ArrayList<>();
        currentNode = root;
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            currentNode = currentNode.children.get(c);
            if (currentNode == null) {
                break;
            }
            results.add(currentNode.words.stream().toList());
        }
        while (results.size() < searchWord.length()) {
            results.add(new ArrayList<>());
        }
        return results;
    }

    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        Solution1268 sol = new Solution1268();
        System.out.println(sol.suggestedProducts(products, searchWord));
    }
}
