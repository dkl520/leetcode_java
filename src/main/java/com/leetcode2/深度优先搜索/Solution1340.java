package com.leetcode2.深度优先搜索;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1340 {

    Map<Integer, Integer> memo;

    public int maxJumps(int[] arr, int d) {
        memo = new HashMap<>();
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {

            max = Math.max(max, dfs(arr, i, d));
        }

        return max;
    }

    int dfs(int[] arr, int index, int d) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int left = Math.max(0, index - d);
        int right = Math.min(arr.length - 1, index + d);
        List<Integer> list = new ArrayList<>();
        int i = index - 1;
        while (i >= left) {
            if (arr[index] <= arr[i]) {
                break;
            }
            list.add(i);
            i--;
        }
        i = index + 1;
        while (i <= right) {
            if (arr[index] <= arr[i]) {
                break;
            }
            list.add(i);
            i++;
        }
        int max = 1;
        for (int otherP : list) {
            max = Math.max(max, dfs(arr, otherP, d) + 1);
        }
        memo.put(index, max);
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d = 2;
        Solution1340 sol = new Solution1340();
        sol.maxJumps(arr, d);
    }
}
