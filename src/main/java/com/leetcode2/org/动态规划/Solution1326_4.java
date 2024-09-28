package com.leetcode2.org.动态规划;

import java.util.*;

import java.util.*;

public class Solution1326_4 {
    public int minTaps(int n, int[] ranges) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < ranges.length; i++) {
            q.offer(new int[]{i - ranges[i], i + ranges[i]});
        }

        int pos = 0;
        int ans = 0;

        while (pos < n) {
            int ref = -1;
            if (q.isEmpty() || q.peek()[0] > pos) return -1;
            while (!q.isEmpty() && q.peek()[0] <= pos) {
                ref = Math.max(ref, q.poll()[1]);
            }
            pos = ref;
            ans += 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] ranges = {7, 8, 10, 0, 12, 0, 7, 2, 10, 12, 11, 12, 4, 2, 17, 6, 6, 9, 6, 17, 10, 8, 8, 16, 10, 11, 16, 2, 16, 3, 13, 10, 15, 3, 10, 14, 4, 15, 4, 14, 5, 3, 9,
                14, 16, 11, 7, 15, 15, 4, 1, 17, 5, 9, 6, 15, 11, 10, 0, 4, 7, 12, 12, 15, 17, 15, 15, 17, 2, 16, 7, 12, 12, 7, 12, 17, 17, 11, 0, 9, 11, 7,
                10, 13, 5, 13,
                7, 5, 16, 11, 5, 15, 1, 8, 10, 3, 5, 16, 16, 8, 9, 14, 2, 10, 9, 13, 11, 4, 7, 4, 15, 1, 13, 9, 0, 4, 16, 0, 11, 6, 1, 3, 6, 12, 9,
                12, 1, 7, 7, 8, 14, 7, 10, 13, 4, 11, 13, 16, 8, 0, 16, 0, 2, 14, 0, 2, 9, 16, 6, 12, 10, 0, 8, 9, 17, 0, 7, 11, 9, 0, 15, 3, 8, 15, 12, 1, 2,
                16, 3, 15, 0, 17, 11, 4, 3, 0, 12, 10, 0, 9, 7, 3, 16, 10, 5, 12, 10, 14,
                11, 3, 17, 2, 7, 5, 9, 4, 12, 17, 7, 9, 12, 15, 3, 5, 8, 2, 0, 8, 12, 10, 17, 1, 5, 4, 11, 15, 3, 13, 2, 10, 15, 12, 9, 3, 0, 3, 4, 9, 13, 7, 14, 15,
                10, 17, 9, 7, 9, 11, 1, 8, 12, 7, 0, 14, 8, 17, 11, 5, 15, 2, 5, 17, 11, 12, 16, 17, 5, 11, 1, 15, 15, 12, 0, 8, 11, 2, 8, 13, 15, 7, 8, 0, 12, 2, 12,
                11, 15, 3, 5, 12, 10, 15, 16, 17, 15, 6, 8, 8, 4, 15, 6, 3, 16, 0, 7, 17,
                6, 4, 10, 2, 3, 3, 4, 12, 5, 14, 9, 3, 17, 4, 8, 17, 17, 12, 14, 17, 5, 4, 1, 1, 6, 12, 7, 2, 11, 17, 3, 13, 1, 13, 7, 9, 5, 11, 1, 9, 3, 2, 5, 11, 5, 8, 3, 13,
                17, 2, 3, 17, 1, 14, 1, 9, 7, 3, 3, 11, 12, 1, 16, 0, 7, 4, 7, 9, 14, 6, 10, 6, 16, 7, 9, 3, 9, 8, 7, 5, 12, 8, 5, 15, 2, 8, 2, 5, 17, 9, 17, 7, 8, 10,
                9, 17, 7, 0, 10};
        int n = 394;
        long startTime = System.currentTimeMillis();
        System.out.println(new Solution1326_4().minTaps(n, ranges));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

