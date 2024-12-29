package com.leetcode2.org.数组;

public class Solution3208 {

    public int numberOfAlternatingGroups(int[] colors, int k) {
        if (colors == null || colors.length == 0 || k > colors.length) return 0;

        int n = colors.length;
        int count = 0;

        // 扩展数组，避免重复计算
        int[] extendedColors = new int[2 * n];
        System.arraycopy(colors, 0, extendedColors, 0, n);
        System.arraycopy(colors, 0, extendedColors, n, n);

        for (int i = 0; i < n; i++) {
            if (isAlternatingGroup(extendedColors, i, k)) {
                count++;
            }
        }

        return count;
    }

    private boolean isAlternatingGroup(int[] colors, int start, int k) {
        for (int i = 1; i < k; i++) {
            if (colors[start + i] == colors[start + i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] colors = {0, 0, 1};
        System.out.println(new Solution3208().numberOfAlternatingGroups(colors, 3));
    }
}