package com.leetcode2.org.树状数组;

import java.util.Arrays;

public class Solution2551 {

    public long putMarbles(int[] wt, int k) {
        int n = wt.length;

        // 将相邻的两个元素相加，存储在前一个元素的位置
        for (int i = 0; i < n - 1; ++i)
            wt[i] += wt[i + 1];

        // 对前 n-1 个元素进行排序
        Arrays.sort(wt, 0, n - 1); // 相当于去掉最后一个数

        long ans = 0;

        // 计算前 k-1 个最大值与最小值的差的和
        for (int i = 0; i < k - 1; ++i)
            ans += wt[n - 2 - i] - wt[i];

        return ans;
    }


}
