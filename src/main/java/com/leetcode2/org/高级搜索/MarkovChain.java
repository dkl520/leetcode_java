package com.leetcode2.org.高级搜索;

import java.util.*;

public class MarkovChain {
    // 存储状态转移概率
    private Map<String, Map<String, Integer>> chain;
    private Random random;

    public MarkovChain() {
        chain = new HashMap<>();
        random = new Random();
    }

    // 训练马尔可夫链
    public void train(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];

            // 获取或创建当前词的转移概率map
            Map<String, Integer> transitions = chain.getOrDefault(currentWord, new HashMap<>());

            // 更新转移计数
            transitions.put(nextWord, transitions.getOrDefault(nextWord, 0) + 1);

            // 更新链
            chain.put(currentWord, transitions);
        }
    }

    // 根据当前词选择下一个词
    private String getNextWord(String currentWord) {
        Map<String, Integer> transitions = chain.get(currentWord);
        if (transitions == null || transitions.isEmpty()) {
            return null;
        }

        // 计算所有转移的总数
        int total = transitions.values().stream().mapToInt(Integer::intValue).sum();

        // 随机选择下一个词
        int rand = random.nextInt(total);
        int cumulative = 0;

        for (Map.Entry<String, Integer> entry : transitions.entrySet()) {
            cumulative += entry.getValue();
            if (rand < cumulative) {
                return entry.getKey();
            }
        }

        return transitions.keySet().iterator().next();
    }

    // 生成文本
    public String generateText(String startWord, int numWords) {
        if (!chain.containsKey(startWord)) {
            return "Starting word not found in training data";
        }

        StringBuilder result = new StringBuilder(startWord);
        String currentWord = startWord;

        for (int i = 0; i < numWords - 1; i++) {
            String nextWord = getNextWord(currentWord);
            if (nextWord == null) {
                break;
            }
            result.append(" ").append(nextWord);
            currentWord = nextWord;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // 示例用法
        MarkovChain markov = new MarkovChain();

        // 训练数据
        String text = "你好 世界 你好 朋友 世界 很 美好 朋友 你好";
        String[] words = text.split(" ");
        markov.train(words);

        // 生成新文本
        String generatedText = markov.generateText("你好", 5);
        System.out.println("Generated text: " + generatedText);
    }
}