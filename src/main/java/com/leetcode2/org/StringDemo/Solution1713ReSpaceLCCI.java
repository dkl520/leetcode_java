package com.leetcode2.org.StringDemo;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
public class Solution1713ReSpaceLCCI {
    // 定义哈希函数的模数和基数
    private static final long MOD = Integer.MAX_VALUE;
    private static final long BASE = 41;
    /**
     * 重新布置空格
     *
     * @param dictionary 字典
     * @param sentence   句子
     * @return 最少的未识别字符数
     */
    public int respace(String[] dictionary, String sentence) {
        // 计算字典中所有单词的哈希值
        Set<Long> wordHashes = calculateWordHashes(dictionary);
        // 动态规划：f[i] 表示前 i 个字符的最少未识别字符数
        int[] f = new int[sentence.length() + 1];
        Arrays.fill(f, sentence.length());
        f[0] = 0;
        // 遍历句子中的每个字符
        for (int i = 1; i <= sentence.length(); ++i) {
            // 不进行分割的情况
            f[i] = f[i - 1] + 1;
            // 尝试不同的分割点
            long hashValue = 0;
            for (int j = i; j >= 1; --j) {
                int charValue = sentence.charAt(j - 1) - 'a' + 1;
                hashValue = (hashValue * BASE + charValue) % MOD;

                // 如果分割出的子串在字典中，更新 f[i]
                if (wordHashes.contains(hashValue)) {
                    f[i] = Math.min(f[i], f[j - 1]);
                }
            }
        }
        // 返回整个句子的最少未识别字符数
        return f[sentence.length()];
    }
    /**
     * 计算字典中所有单词的哈希值
     *
     * @param dictionary 字典
     * @return 哈希值集合
     */
    private Set<Long> calculateWordHashes(String[] dictionary) {
        Set<Long> hashValues = new HashSet<>();
        for (String word : dictionary) {
            hashValues.add(calculateHash(word));
        }
        return hashValues;
    }
    /**
     * 计算单个单词的哈希值
     *
     * @param word 单词
     * @return 哈希值
     */
    private long calculateHash(String word) {
        long hashValue = 0;
        for (int i = word.length() - 1; i >= 0; --i) {
            int charValue = word.charAt(i) - 'a' + 1;
            hashValue = (hashValue * BASE + charValue) % MOD;
        }
        return hashValue;
    }

    public static void main(String[] args) {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        System.out.println(new Solution1713ReSpaceLCCI().respace(dictionary, sentence));
    }
}
