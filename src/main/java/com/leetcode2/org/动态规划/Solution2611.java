package com.leetcode2.org.动态规划;

import java.util.*;
import java.util.stream.IntStream;

public class Solution2611 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {

        Queue<List<Integer>> changeReward = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.get(1), a.get(1))
        );
        for (int i = 0; i < reward1.length; i++) {
            List<Integer> line = new ArrayList<>();
            line.add(i);
            line.add(reward1[i] - reward2[i]);
            changeReward.add(line);
        }
        List<Integer> reward1IndexList = new ArrayList<>();
        int result = 0;
        while (k > 0) {
            int reward1Index = changeReward.poll().get(0);
            result += reward1[reward1Index];
            reward1IndexList.add(reward1Index);
            k--;
        }
        for (int i = 0; i < reward2.length; i++) {
            if (!reward1IndexList.contains(i)) {
                result += reward2[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] reward1 = {1, 1, 3, 4};
        int[] reward2 = {4, 4, 1, 1};
        int k = 2;

        Solution2611 solution2611 = new Solution2611();
        System.out.println(solution2611.miceAndCheese(reward1, reward2, k));
    }
}
