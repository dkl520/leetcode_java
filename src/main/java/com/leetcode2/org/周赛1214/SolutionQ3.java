package com.leetcode2.org.周赛1214;

import java.util.List;

public class SolutionQ3 {
    public int beautifulSplits(List<Integer> nums) {

        int n = nums.size();
        int[][] f = new int[n + 1][n + 1];
        // 预处理 f[i][j] 表示以 nums[i] 和 nums[j] 为开头的最长公共前缀
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums.get(i).equals(nums.get(j))) {
                    f[i][j] = f[i + 1][j + 1] + 1;
                }
            }
        }
        int ans = 0;
        // 枚举前两个子数组的右端点
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j + 1 < n; j++) {
                int len1 = i + 1, len2 = j - i, len3 = n - 1 - j;
                // 判断子数组 1 是不是子数组 2 的前缀，以及子数组 2 是不是子数组 3 的前缀
                if ((len1 <= len2 && f[0][i + 1] >= len1) || (len2 <= len3 && f[i + 1][j + 1] >= len2)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
