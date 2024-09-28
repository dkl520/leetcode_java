package com.leetcode2.深度优先搜索;

public class Solution115 {

    public int numDistinct(String s, String t) {
        int[] result = new int[1];
        dfs(s, t, result, 0, 0);
        return result[0];
    }

    private void dfs(String s, String t, int[] result, int tIndex, int sIndex) {
        if (tIndex == t.length()) {
            result[0]++;
            return;
        }
        for (int i = sIndex; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == t.charAt(tIndex)) {
                dfs(s, t, result, tIndex + 1, i + 1);
            }
        }
    }


    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        Solution115 solution115 = new Solution115();
        System.out.println(solution115.numDistinct(s, t));
    }

}
