package com.leetcode2.org.dynamicPrograming;

import java.util.Arrays;

public class Solution3291_2 {
    // 定义字典树节点类
    static class TrieNode {
        TrieNode[] nxt; // 存储子节点的数组，大小为26，对应26个小写字母

        TrieNode() {
            nxt = new TrieNode[26]; // 初始化子节点数组
            Arrays.fill(nxt, null); // 将所有子节点置为null
        }
    }

    TrieNode root = new TrieNode(); // 定义字典树的根节点
    int[] dp = new int[100010]; // 定义动态规划数组，dp[i]表示将target的前i个字符分割的最小次数
    final int INF = 0x3f3f3f3f; // 定义一个很大的数，表示不可达的状态

    // 主方法：计算最少分割次数
    public int minValidStrings(String[] words, String target) {
        int n = words.length, m = target.length(); // n为单词数组长度，m为目标字符串长度
        Arrays.fill(dp, INF); // 初始化dp数组为无穷大，表示初始状态不可达
        // 将所有单词插入字典树
        for (String word : words) {
            insert(word);
        }
        dp[0] = 0; // 空字符串的分割次数为0
        // 遍历目标字符串的每个子串
        for (int l = 1; l <= m; l++) { // l为子串的起始位置
            TrieNode cur = root; // 从字典树的根节点开始
            for (int r = l; r <= m; r++) { // r为子串的结束位置
                int index = target.charAt(r - 1) - 'a'; // 获取当前字符的索引
                if (cur.nxt[index] != null) { // 如果字典树中存在该字符
                    // 更新dp[r]，将当前子串作为一个部分进行分割
                    if (dp[l - 1] + 1 < dp[r]) {
                        dp[r] = dp[l - 1] + 1;
                    }
                    cur = cur.nxt[index]; // 移动到下一个节点
                } else {
                    break; // 如果当前字符不在字典树中，跳出循环
                }
            }
        }
        // 如果dp[m]仍为INF，说明目标字符串无法由给定单词组成，返回-1；否则返回dp[m]
        return dp[m] == INF ? -1 : dp[m];
    }

    // 插入单词到字典树
    public void insert(String word) {
        TrieNode cur = root; // 从根节点开始
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // 计算字符对应的索引
            if (cur.nxt[index] == null) { // 如果当前字符不存在于字典树中
                cur.nxt[index] = new TrieNode(); // 创建一个新节点
            }
            cur = cur.nxt[index]; // 移动到下一个节点
        }
    }



    public static void main(String[] args) {
        String[] words = new String[]{"adaeabcabdcaabbeceeadeaebcdddeadcbceeeadddabdc", "a"};
        String target = "beeea";
        System.out.println(new Solution3291_2().minValidStrings(words, target));


    }
}
