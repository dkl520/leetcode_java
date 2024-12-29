package com.leetcode2.org.数组;

import java.util.HashMap;
import java.util.Map;

public class Solution1010 {
    public int numPairsDivisibleBy60(int[] time) {

        int n = time.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
            max = Math.max(max, time[i]);
        }
        int result = 0;
        int maxIndex = (max * 2) / 60;
        for (int i = 0; i < n; i++) {
            int cur = time[i];
            map.put(time[i], map.getOrDefault(cur, 0) - 1);
            for (int j = 1; j <= maxIndex; j++) {
                int target = j * 60;
                if (target < cur) continue;

                if (map.containsKey(target - cur)) {
                    result += map.get(target - cur);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] time = new int[]{30, 20, 150, 100, 40};
        Solution1010 solution1010 = new Solution1010();
        System.out.println(solution1010.numPairsDivisibleBy60(time));


    }
}
