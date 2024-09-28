package com.leetcode2.org.前缀和;

public class Solution3096 {
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += possible[i] == 0 ? -1 : 1;
            prefix[i] = count;
        }
        count = 0;
        for (int j = n - 1; j >= 0; j--) {
            count += possible[j] == 0 ? -1 : 1;
            suffix[j] = count;
        }
        for (int i = 0; i < n-1; i++) {
            if (prefix[i] > suffix[i+1] ) {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] possible = {1, 0, 1, 0};
        System.out.println(new Solution3096().minimumLevels(possible));
    }
}
