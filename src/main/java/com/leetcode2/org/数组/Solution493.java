package com.leetcode2.org.数组;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution493 {
    static class BIT {
        int size;
        int[] cnt;

        public BIT(int size) {
            this.size = size;
            this.cnt = new int[size + 1];
        }

        int lowBit(int x) {
            return x & (-x);
        }

        int query(int x) {
            int sum = 0;
            while (x > 0) {
                sum += this.cnt[x];
                x -= lowBit(x);
            }
            return sum;
        }

        void update(int index, int val) {

            while (index <= this.size) {
                this.cnt[index] += val;
                index += lowBit(index);
            }
        }
    }

    public int reversePairs(int[] nums) {
        Map<Long, Integer> rankList = new HashMap<>();
        TreeSet<Long> treeSet = new TreeSet<>();
        int n = nums.length;
        for (int num : nums) {
            treeSet.add((long) num);
            treeSet.add(num * 2L);
        }
        AtomicInteger curRank = new AtomicInteger(1);
        treeSet.forEach((val) -> {
            rankList.put(val, curRank.getAndIncrement());
        });
        BIT tree = new BIT(treeSet.size());
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur_rank = rankList.get((long)nums[i]);
            result += tree.query(cur_rank - 1);
            tree.update(rankList.get(nums[i] * 2L), 1);
        }
        return result;

    }

    public static void main(String[] args) {
        Solution493 solution493 = new Solution493();
        int[] pairs = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(
                solution493.reversePairs(pairs)

        );

    }


}
