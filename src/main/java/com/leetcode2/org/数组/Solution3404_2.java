package com.leetcode2.org.数组;


import java.util.*;

public class Solution3404_2 {

    public int bisectLeft(List<Integer> arr, int key) {
        int low = 0;
        int high = arr.size();

        // Binary search
        while (low < high) {
            int mid = low + (high - low) / 2; // Avoid overflow
            if (arr.get(mid) < key) {
                low = mid + 1; // Narrow down to the right half
            } else {
                high = mid; // Narrow down to the left half
            }
        }

        return low; // Low is the leftmost insertion point
    }

    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        Map<Double, List<Integer>> memo = new HashMap<>();
        for (int s = n - 1; s >= 2; s--) {
            for (int r = s - 2; r >= 0; r--) {
                memo.computeIfAbsent((double) nums[s] / nums[r], TreeSet ->
                        new ArrayList<>()).add(r);
            }
        }
        int ans = 0;
        for (int p = 0; p < n - 2; p++) {
            for (int q = p + 2; q < n; q++) {
                double val = (double) nums[p] / nums[q];
                if (memo.containsKey(val)) {
                    var list = memo.get(val);
                    Collections.sort(list);
                    var index = bisectLeft(list, q + 2);
                    if (index >= 0) {
                        ans += list.size() - index;
                    } else {
                        ans += list.size() - (-(index + 1));
                    }
                }

            }
        }
        return ans;
    }



}
