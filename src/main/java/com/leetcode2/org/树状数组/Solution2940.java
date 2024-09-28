package com.leetcode2.org.树状数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2940 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        List<Integer>[] lists = new ArrayList[heights.length];
        int n = heights.length;
        int[] result = new int[queries.length];
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(i);
            for (int j = i + 1; j < n; j++) {
                if (heights[i] < heights[j]) {
                    list.add(j);
                }
            }
            lists[i] = list;
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            if (query[0] > query[1]) {
                queries[i] = new int[]{query[1], query[0]};
            }
        }


        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            List<Integer> list1 = lists[query[0]];
            List<Integer> list2 = lists[query[1]];
            result[i] = list2.stream()
                    .filter(list1::contains)
                    .findFirst()
                    .map(Number::intValue)
                    .orElse(-1);

        }
        return result;
    }

    public static void main(String[] args) {
//        int[] heights = {1, 2, 1, 2, 1, 2};
//
//        int[][] queries = {
//                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
//                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
//                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
//                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
//                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5},
//                {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}
//        };
        int[] heights = {6, 4, 8, 5, 2, 7};

        int[][] queries = {
                {0, 1},
                {0, 3},
                {2, 4},
                {3, 4},
                {2, 2}
        };


        System.out.println(Arrays.toString(new Solution2940().leftmostBuildingQueries(heights, queries)));

    }

}
