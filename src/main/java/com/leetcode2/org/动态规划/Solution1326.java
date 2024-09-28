package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution1326 {

    public int minTaps(int n, int[] ranges) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < ranges.length; i++) {
            int[] curLineSegment = new int[2];
            curLineSegment[0] = i - ranges[i];
            curLineSegment[1] = i + ranges[i];
            list.add(curLineSegment);
        }
        list.sort(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] target = {0, n};
        int count = 0;
        int finalCount = Integer.MAX_VALUE;
        Map<String, Integer> dictionary = new HashMap<>();
        int result = dfs(target, count, list, finalCount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    int dfs(int[] target, int count, List<int[]> list, int finalCount ) {

        for (int i = 0; i < list.size(); i++) {
            int[] curLineSegment = list.get(i);
            if (curLineSegment[0] <= target[0] && curLineSegment[1] >= target[1]) {
                return count + 1;
            }
            if (curLineSegment[0] <= target[0] && curLineSegment[1] > target[0]) {
                finalCount = Math.min(finalCount, dfs(new int[]{curLineSegment[1], target[1]}, count + 1, list, finalCount));
            }
        }
        return finalCount;
    }


    public static void main(String[] args) {
        int[] ranges = {0, 0, 0, 1, 4, 2, 2, 2, 2, 4, 0, 0, 0, 5, 4, 0, 0, 5, 3, 0, 1, 1, 5, 1, 1, 2, 4,
                1, 0, 4, 3, 5, 1, 0, 3, 3, 4, 2, 2, 4, 3, 1, 1, 0, 4, 0, 2, 1, 4, 0, 0, 3, 3, 1,
                1, 4, 4, 2, 0, 3, 4, 0, 1, 5, 3, 0, 1, 0, 2};
        int n = 68;
        System.out.println(new Solution1326().minTaps(n, ranges));

    }
}
